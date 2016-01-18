$(function(){
	$("#Merchant_No").bind("blur",function(){
		var username = $("#Merchant_No").val();  
		if(username==null||username==""){
			$("#mer_error").html("请填写用户名！");
		} 
	});
	$("#Merchant_No").bind("focus",function(){
		$("#mer_error").html("");
	});
	$("#pwd").bind("blur",function(){
		var password = $("#pwd").val();  
		if(password==null||password==""){
			$("#pwd_error").html("请填写密码！");
		}
	});
	$("#pwd").bind("focus",function(){
		$("#pwd_error").html("");
	});
	 
});