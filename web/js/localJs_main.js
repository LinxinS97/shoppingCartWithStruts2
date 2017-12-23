$(document).ready(function(){
    let $Img = $(".main-img");
    let maxSize = 71;
    let maxWidth = 66.5;
    $Img.each(function(){
        let $this = $(this);
        if($this.height() > maxSize){
            let scale = maxSize / $this.height();
            let padding = (maxWidth - $this.width() * scale) / 2;
            $this.css("padding-left", padding);
            $this.css("padding-right", padding);
        }else if($this.height() < maxSize){
            $this.css("padding-top", (maxSize - $this.height()) / 2);
            $this.css("padding-bottom", (maxSize - $this.height()) / 2);
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

    $.ajax({
        type:"get",
        url:"ifLogin",
        data:{},
        success:function(data){
            if(data.flag === "true"){
                $("#nav_loginInfo_unLogin").attr("style","display:none");
                $("#nav_loginInfo_isLogin").attr("style","display:block");
                $("#info_unLogin").attr("style", "display:none");
                $("#info_isLogin").attr("style", "display:block");
                $("#tb_isLogin").attr("style","display:");
                $("#tb_unLogin").attr("style","display:none");
                $("#loginCenter").attr("style","margin-bottom:55px");
            }else{
                $("#info_unLogin").attr("style", "display:block");
                $("#info_isLogin").attr("style", "display:none");
                $("#nav_loginInfo_unLogin").attr("style","display:block");
                $("#nav_loginInfo_isLogin").attr("style","display:none");
                $("#tb_isLogin").attr("style","display:none");
                $("#tb_unLogin").attr("style","display:");
            }
        }
    })
});