$(document).ready(function(){
    $.ajax({
        type:"get",
        url:"ifLogin",
        data:{},
        success:function(data){
            if(data.flag === "true"){
                $("#loginCenter").attr("style","margin-bottom:55px");
            }
        }
    });

    $("#itemPrice").change(function(){
       if($(this).val() < 0)
           $(this).val(0);
    });
    $("#itemStock").change(function(){
        if($(this).val() < 0)
            $(this).val(0);
    });

    $(".delete-item").click(function () {
        let $this = $(this);
        let itemId = $this.attr("itemId");

        console.log(itemId);
        $.ajax({
            type:   "get",
            url:    "deleteItem",
            data:{
                itemId: itemId
            },
            success:function () {
                $this.parent().parent().parent().parent().remove();
            }
        })
    });

    $("#itemImg").change(function(){
        let file = this.files[0];
        if (window.FileReader) {
            let reader = new FileReader();
            reader.readAsDataURL(file);
            //监听文件读取结束后事件
            reader.onloadend = function (e) {
                $("#img-show").attr("src", e.target.result);    //e.target.result就是最后的路径地址
            }
        }
    });

    $(".buy-number").change(function () {
        let $this = $(this);
        if($this.val() > parseInt($this.attr("stock"))){
            $this.val($this.attr("stock"));
        } else if($this.val() < 0){
            $this.val(0);
        }
    });

    $(".operate").mousedown(function () {
        let flag = false;
        let $this = $(this);
        $.ajax({
            type:"get",
            url:"ifLogin",
            data:{},
            success:function (data) {
                if(data.flag === "true"){
                    if($this.hasClass("operate-purchase")){
                        flag = true;
                    } else{
                        $.ajax({
                            type:"get",
                            url:"operateCart",
                            data:{
                                itemId:$this.attr("itemId"),
                                num:$("#item-num").val()
                            },
                            success:function (data) {
                                if(data.flag === "true")
                                    $("#myModal_itemExist").modal('show');
                                else
                                    $("#myModal_addSuccess").modal('show');
                            }
                        });
                    }
                }
                else{
                    $("#myModal_login").modal('show');
                }
            }
        });
        return flag;
    });

});