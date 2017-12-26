$(function () {
    $("#my-nav-dropdown").css("display", "none");

    $("#select-all").click(function () {
        let $this = $(this);
        let $sum = $("#sum-num");
        let sum = $sum.html() * 1;
        let $sumP = $("#sum-price");
        let sumP = $sumP.html() * 1.00;
        let $checkboxes = $(".item-is-check");

        if($this.hasClass("active")){
            $this.removeClass("active");
            $checkboxes.each(function () {
                $(this).prop("checked", false);
                $(this).parent().parent().removeClass("checked");
            });
            $sum.html(0);
            $sumP.html("0.00");
        } else{
            $this.addClass("active");
            sum = 0;
            $checkboxes.each(function () {
                $(this).prop("checked", true);
                $(this).parent().parent().addClass("checked");
                sum+=1;
                sumP += $(this).parent().parent().find(".sum-tag").html() * 1.00;  //找到所有的价格结点，求和
            });
            $sum.html(sum);
            $sumP.html(sumP);
        }
    });

    $(".item-is-check").change(function () {
        let $this = $(this);
        let $sum = $("#sum-num");
        let sum = $sum.html()*1;
        let $sumP = $("#sum-price");
        let sumP = $sumP.html() * 1.00;
        let $checkboxes = $(".item-is-check");

        if($this.prop("checked") === true){
            let flag = true;
            $this.parent().parent().addClass("checked");
            sumP += $this.parent().parent().find(".sum-tag").html() * 1.00;  //找到价格结点，求和
            $checkboxes.each(function () {
                if($(this).prop("checked") === false)
                    flag = false;
            });
            if(flag)
                $("#select-all").addClass("active");
            $sum.html(++sum);
            $sumP.html(sumP);
        } else{
            sumP -= $this.parent().parent().find(".sum-tag").html() * 1.00;  //找到价格结点，求和
            $("#select-all").removeClass("active");
            $(this).parent().parent().removeClass("checked");
            $sum.html(--sum);
            $sumP.html(Number(sumP).toFixed(2));
        }
    });
    
    $(".num").change(function () {
        let $this = $(this);
        let num = $this.val() * 1;
        let price = $this.parent().prev().find(".price-tag").html() * 1.00;
        console.log(price);
        let stock = $this.next().find(".stock").html() * 1.00;

        if(num < 1)
            $this.val(1);
        else if(num > stock)
            $this.val(stock);

        console.log(Number($this.val()).toFixed(2) * price);
        $this.parent().next().find(".sum-tag").html(Number(price * $this.val()).toFixed(2));
    });

    $(".delete-btn-page").click(function () {
        let $this = $(this);
        $.ajax({
            type:   "get",
            url:    "deleteCart",
            data:{
                itemId:$this.attr("itemId")
            },
            success:function(data) {
                if(data.flag==="true")
                    $this.parent().parent().remove();
            }
        })
    })
});