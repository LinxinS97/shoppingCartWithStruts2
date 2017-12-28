$(function () {

    $('#apprise').rating({
        showCaption: true,
        step: 1,
        size: 'xs',
        showClear: false,
        clearCaption: "请评分",
        starCaptions: {1: '非常差评', 2: '差评', 3: '中评', 4: '好评', 5: '非常好评'}
    });


    $(".delivery").click(function () {
        $("#delivery-c").attr("orderId", $(this).attr("orderId"));
        $("#delivery-modal").modal('show');
    });

    $("#delivery-c").click(function () {
        $.ajax({
            type:"get",
            url:"alterOrder",
            data:{
                orderId:$(this).attr("orderId"),
                options:"delivery"
            },
            success:function () {
                location.reload();
            }
        })
    });

    //判断密码是否为空
    $("#psw").blur(function(){
        if($("#psw").val() === ""){
            $("#psw-div").addClass("has-error");
            $("#psw-span").attr("style","display:block");
        }else{
            $("#psw-div").attr("class","form-group has-success has-feedback");
            $("#psw").attr("for","inputSuccess1");
            $("#psw-span").attr("style","display:none");
            $("#helpBlock2").attr("style","display:none");
            $("#helpBlock3").attr("style","display:none");
        }
    });

    $(".get").click(function () {
        $("#get-c").attr("orderId", $(this).attr("orderId"));
        $("#get-modal").modal('show');
    });
    
    $("#get-c").click(function () {
        let $psw = $("#psw");
        let $pswd = $("#psw-div");
        let $psws = $("#psw-span");

        if($psw.val() === "" && !$pswd.hasClass("has-error")){
            $pswd.addClass("has-error");
            $psws.attr("style","display:block");
            $("#helpBlock2").attr("style","display:block");
        } else{
            $.ajax({
                type:"post",
                url:"alterOrder",
                data:{
                    orderId:$(this).attr("orderId"),
                    psw:    $psw.val(),
                    options:"get"
                },
                success:function (data) {
                    if(data.flag === "success")
                        location.reload();
                    else if(data.flag === "pswError"){
                        $pswd.attr("class","form-group has-error has-feedback");
                        $psws.attr("style","display:block");
                        $("#helpBlock3").attr("style","display:block");
                    }
                }
            })
        }
    });

    $(".comment").click(function () {
        let $commentC = $("#comment-c");
        $commentC.attr("itemId", $(this).attr("itemId"));
        $commentC.attr("orderId", $(this).attr("orderId"));
        $("#comment-modal").modal('show');
    });

    $("#comment-c").click(function () {
        let $apprise = $("#apprise");
        let $comment = $("#comment-textarea");

        console.log($apprise.val() + " " + $comment.val());
        if($apprise.val() === ''){
            $("#helpBlock4").attr("style","display:block");
            return false;
        } else{
            $.ajax({
                type:"post",
                url:"addComment",
                data:{
                    apprise:$apprise.val(),
                    comment:$comment.val(),
                    itemId: $(this).attr("itemId"),
                    orderId:$(this).attr("orderId"),
                    options:"comment"
                },
                success:function (data) {
                    if(data.flag === "success")
                        location.reload();
                }
            })
        }
    })
});