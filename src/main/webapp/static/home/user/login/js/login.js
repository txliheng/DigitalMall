$(function(){
	$('button').click(function(){
		if(($.trim($("input[name='username']").val())=='')&&
		   ($.trim($("input[name='password']").val())=='')){
			$("#J_Message").removeAttr("style");
			$("#J_Message").css("display","block;");
			$("#J_Message > p").html("请输入账户名和密码");
			return false;
		}
		if($.trim($("input[name='username']").val())==''){
			$("#J_Message").removeAttr("style");
			$("#J_Message").css("display","block;");
			$("#J_Message > p").html("请输入账户名");
			return false;
		}
		if($.trim($("input[name='password']").val())==''){
			$("#J_Message").removeAttr("style");
			$("#J_Message").css("display","block;");
			$("#J_Message > p").html("请输入密码");
			return false;
		}
		return true;

	})

});