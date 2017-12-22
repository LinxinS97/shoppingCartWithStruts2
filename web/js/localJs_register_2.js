/**
 * helpBlock2:密码为空
 * helpBlock3:密码确认为空
 * helpBlock4:两次密码输入不一致
 * helpBlock5:手机号码为空
 * helpBlock6:手机号码格式不标准
 */

$(document).ready(function() {

    $("#psw").blur(function () {

        if ($("#psw").val() === "") {
            $("#psw-div").addClass("has-error");
            $("#wrong2").attr("style", "display:block");
        } else {
            $("#psw-div").attr("class", "form-group has-success has-feedback");
            $("#psw").attr("for", "inputSuccess1");
            $("#wrong2").attr("style", "display:none");
            $("#helpBlock2").attr("style", "display:none");
            $(".helpBlockServer").attr("style", "display:none");
        }
    });

    $("#psw-conform").blur(function () {

        if ($("#psw-conform").val() === "") {
            $("#psw-conform-div").addClass("has-error");
            $("#wrong3").attr("style", "display:block");
            $("#helpBlock3").attr("style", "display:block");
            $("#helpBlock4").attr("style", "display:none");
        } else {
            $("#psw-conform-div").attr("class", "form-group has-success has-feedback");
            $("#psw-conform").attr("for", "inputSuccess1");
            $("#wrong3").attr("style", "display:none");
            $("#helpBlock3").attr("style", "display:none");
            $("#helpBlock4").attr("style", "display:none");
            $(".helpBlockServer").attr("style", "display:none");
        }
    });

    $("#userName").blur(function () {

        if ($("#userName").val() === "") {
            $("#userName-div").addClass("has-error");
            $("#wrong4").attr("style", "display:block");
            $("#helpBlock5").attr("style", "display:block");
            $("#helpBlock6").attr("style", "display:none");
        } else {
            $("#userName-div").attr("class", "form-group has-success has-feedback");
            $("#userName").attr("for", "inputSuccess1");
            $("#wrong4").attr("style", "display:none");
            $("#helpBlock5").attr("style", "display:none");
            $("#helpBlock6").attr("style", "display:none");
            $(".helpBlockServer").attr("style", "display:none");
        }
    });

    $("#bt-submit").click(function(){
        if($("#userName-div").hasClass("has-error")
            || $("#psw-div").hasClass("has-error")
            || $("#psw-conform-div").hasClass("has-error")){
            return false;
        }else if($("#userName").val() == ""
            || $("#psw").val() == ""
            || $("#psw-conform").val() == ""){
            return false;
        }
    });

    $("#img").change(function(){
        var file = this.files[0];
        if (window.FileReader) {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            //监听文件读取结束后事件
            reader.onloadend = function (e) {
                $("#img-show").attr("src", e.target.result);    //e.target.result就是最后的路径地址
            }
        }
    })
});