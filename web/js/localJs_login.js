$(document).ready(function(){
	
	//判断用户名是否为空
	$("#tel").blur(function(){
		if($("#tel").val() === ""){
			$("#tel-div").addClass("has-error");
			$("#tel-span").attr("style","display:block");
		}else{
			$("#tel-div").attr("class","form-group has-success has-feedback");
			$("#tel").attr("for","inputSuccess1");
			$("#tel-span").attr("style","display:none");
			$("#helpBlock1").attr("style","display:none");
			$("#helpBlock4").attr("style","display:none");
		}
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
	
	//用户名或密码为空则不允许登录
	$("#bt-submit").click(function(){
		var flag = false;
		let $this = $(this);
		if($("#tel").val() === "" && !$("#tel-div").hasClass("has-error")){
			$("#tel-div").addClass("has-error");
			$("#tel-span").attr("style","display:block");
			$("#helpBlock1").attr("style","display:block");
			return flag;
		}else if($("#psw").val() === "" && !$("#psw-div").hasClass("has-error")){
			$("#psw-div").addClass("has-error");
			$("#psw-span").attr("style","display:block");
			$("#helpBlock2").attr("style","display:block");
			return flag;
		}else if($("#psw-div").hasClass("has-error") || $("#psw-div").hasClass("has-error")){
			return flag;
		}else{
			//alert(""+$("#psw").val());
            //console.log($(".options:checked").val());
			$.ajax({
				type:"post",
				url:"login",
				data:{
					tel : ""+$("#tel").val(),
                    psw : ""+$("#psw").val(),
					options : ""+$(".options:checked").val()
				},
				success:function(data){
					if(data.flag === "success"){
					    if($this.hasClass("modal-button")){
                            location.reload();
                        }else{
                            if(data.options === "seller")
                                location.href = "Management?requestType=1";
                            else
                                location.href = "Welcome";
                        }

					}else if(data.flag === "pswError"){
						$("#psw-div").attr("class","form-group has-error has-feedback");
						$("#psw-span").attr("style","display:block");
						$("#helpBlock3").attr("style","display:block");
						flag = false;
					}else if(data.flag === "unknownUserName"){
						$("#tel-div").addClass("has-error");
						$("#tel-span").attr("style","display:block");
						$("#helpBlock4").attr("style","display:block");
						flag = false;
					}
				}
			});
			return flag;
		}
	})
});