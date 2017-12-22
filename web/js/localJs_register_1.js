/**
 * helpBlock1:用户名为空
 * helpBlock7:用户名已存在
 */

$(document).ready(function(){

    $("#ajax-div").attr("style", "display:");
    $("#info-div").attr("style", "display:none");
    $("#td2").attr("style", "");
    $("#td1").attr("style", "border-top: 1px #ec971f solid; color: #ec971f;");

    $("#tel").blur(function(){
		if($("#tel").val() === ""){
			$("#tel-div").addClass("has-error");
			$("#wrong1").attr("style","display:block");
			$("#helpBlock1").attr("style", "display:block");
		}else{
			$.ajax({
				type:"get",
				url:"ifTelNumExist.action",
				data:{
					tel : $("#tel").val()
				},
				success:function(data){
					if(data.status === "true"){
						$("#tel-div").addClass("has-error");
						$("#tel-span").attr("style","display:block");
						$("#helpBlock7").attr("style","display:block");
					}else{
						$("#helpBlock7").attr("style","display:none");
					}
				}
			});
			$("#tel-div").attr("class","form-group has-success has-feedback");
			$("#tel").attr("for","inputSuccess1");
			$("#wrong1").attr("style","display:none");
			$("#helpBlock1").attr("style","display:none");
			$(".helpBlockServer").attr("style","display:none");
		}
	});

    $("#bt-submit-1").click(function(){
    	if($("#helpBlock7").attr("style") === "display:block")
    		return false;
	})

});