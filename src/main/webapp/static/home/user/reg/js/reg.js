
var MSG_EMAIL_FORMAT_ERROR = '邮箱格式不正确，请重新输入';
var MSG_EMAIL_OK = '';
var MSG_EMAIL_INFO = '请输入你的电子邮箱';
var MSG_EMAIL_EXIST = '此邮箱作为登录名已存在，请更换邮箱或登录';
var MSG_NICK_OK = '';
var MSG_NICK_INFO = '请输入昵称';
var MSG_NICK_EXIST = '此昵称作为登录名已存在，请更换昵称或登录';
var MSG_CELL_CHECK_CODE_INVALID='校验码不正确，请重新输入';
var MSG_CELL_CHECK_CODE_FORMAT_ERROR = '校验码是6位数字，请重新输入';
var MSG_CELL_CHECK_CODE_INFO ='请输入校验码';
var MSG_CELL_CHECK_CODE_OK ='';
var MSG_IMAGE_CHECK_CODE_FORMAT_ERROR ='';
var MSG_IMAGE_CHECK_CODE_INFO ='请输入验证码';
var MSG_IMAGE_CHECK_CODE_OK ='';
var MSG_IMAGE_CHECK_CODE_INVALID ='验证码不正确，请重新输入';
var MSG_MOBILE_INFO = '请输入手机号码';
var MSG_MOBILE_OK = '';
var MSG_MOBILE_FORMAT_ERROR = '手机号码格式不正确，请重新输入';
var MSG_SEND_SMS_INFO ='校验码已发送至你的手机，请查收';
var MSG_SEND_SMS_GENERAL_ERROR ='网络繁忙，请重新获取校验码';


$(function(){

	$('#J_Mobile').focus();
	$('#J_Mobile').blur(function(){
		checkMobileNumber();
	});
	$('#J_CheckCodeInput').blur(function(){
		var imageCode = $('#J_CheckCodeInput').val();

		ajaxCheckImageCode(imageCode);
		
	});
	$('#J_ImgRefresher1').click(function(){
		changeImageCode();
	});
	/**
	 * 按下按钮J_BtnMobileForm,检查MobileForm各输入项格式是否正确，
	 * 正确，校验图片验证码；
	 * 校验成功，发送短信验证码，显示校验短信验证码表单
	 */
	$("#J_BtnMobileForm").click(function(){
		if(!checkMobileForm()) return false;
		var imageCode = $('#J_CheckCodeInput').val();
		$.ajaxSetup({
			dataType : 'json'//返回json
		});
		/**
		 * 校验图片验证码
		 */
		$.post("/user/checkcode/check_image_check_code.do",{imagecode:imageCode},function(result){
			var obj = $('#J_MsgCheckCode');
			
			if(!result.success){
				msgErr(obj,MSG_IMAGE_CHECK_CODE_INVALID);
				changeImageCode();
				
			}else{//图片验证码校验成功,发送短信验证码，显示校验短信验证码表单
				msgOk(obj,MSG_IMAGE_CHECK_CODE_OK)
				sendSmsCode();//发送短信验证码
				$('#J_MobileNumber').html($("#J_Mobile").val());
				$('#J_MobileCode').val("");
				$('#J_MobileCheck').fadeIn(1000,function(){//显示校验短信验证码表单
					
					setTimeout(function(){smsCodeInputDisable(60);},0);
				});
				
			}			
		});
		return false;		
	});

	/**
	 * 隐藏校验短信验证码表单，同时更换图片验证码
	 */
	$("#J_MobileCheck > a").click(function(){
		$('#J_MobileCheck').fadeOut();//隐藏校验短信验证码表单
		changeImageCode();//更换图片验证码
	});
	/**
	 * 发送短信验证码，同时设置定时器
	 */
	$('#J_BtnMobileCode').click(function(){

		if(!checkMobileNumber()) return false;
		sendSmsCode();
		setTimeout(function(){smsCodeInputDisable(60);},0);
		
	});
	/**
	 * 按下校验短信验证码表单提交按钮J_BtnMobileCodeForm,
	 * 校验短信验证码，成功，提交表单J_MobileForm，
	 * 检查mobile作为登录名是否存在
	 */
	$('#J_BtnMobileCodeForm').click(function(){
		var mobileCode = $("#J_MobileCode").val();
		if(!checkMobileCode()) return false;
		$.ajaxSetup({  
	        dataType : 'json'
	    }); 
		$.post("/user/checkcode/check_cell_checkcode.do",{smscode:mobileCode},function(result){
			var  msgResendCode = $("#J_MsgResendCode");
			if(!result.success){
				msgErr(msgResendCode,MSG_CELL_CHECK_CODE_INVALID);
			
			}else{
				msgOk(msgResendCode,MSG_CELL_CHECK_CODE_OK);
				//检查mobile作为登录名是否存在
				$("#J_MobileForm").attr("action","/user/check_cell.do").submit();
			}
		});
		return false;
	});
	$("[href='#nogo']").mouseover(function(){
		$("#J_TipsMobileUsed").show();
	});
	$("[href='#nogo']").mouseout(function(){
		$("#J_TipsMobileUsed").hide();
	});
	$('#J_Email').focus();
	$('#J_Email').blur(function(){
		checkEmail();
	});
	/**
	 * 检查email作为登录名是否存在
	 */
	$("#J_BtnEmailForm").click(function(){
		if(!checkEmail()) return false;
		var email = $('#J_Email').val();
		$.ajaxSetup({
			dataType : 'json'//返回json
		});
		$.post("/user/check_email.do",{email:email},function(result){
			var msgEmail = $("#J_MsgEmail");

			if(!result.success){//存在
				msgErr(msgEmail,MSG_EMAIL_EXIST);
			}else{//不存在，就发送用户确认信
				msgOk(msgEmail,MSG_EMAIL_OK);
				$('#J_EmailForm').attr("action","/user/send_email.do").submit();
			}
		});
		return false;
	});
	$('#J_Password').blur(function(){
		var msgPwd = $("#J_MsgInfoForm");
		if($.trim($("#J_Password").val())==''){
			msgErr(msgPwd,"密码不能为空");
			$('#J_Password').focus();
			return false;		
		}
	});
	$('#J_RePassword').blur(function(){
		var msgPwd = $("#J_MsgInfoForm");
		if(($.trim($("#J_RePassword").val())!='')&&
			($.trim($("#J_Password").val())!='')&&
			($.trim($("#J_RePassword").val())!=$.trim($("#J_Password").val()))){
				msgErr(msgPwd,"两次输入密码不相等,请重新输入密码");
				$('#J_Password').val("");
				$('#J_RePassword').val("");
				$('#J_Password').focus();
				return false;
		}
		if($.trim($("#J_RePassword").val())==''){			
			msgErr(msgPwd,"再次输入你的密码");
			$('#J_RePassword').focus();	
			return false;	
		}
		if(($.trim($("#J_RePassword").val())!='')&&
				($.trim($("#J_Password").val())!='')&&
				($.trim($("#J_RePassword").val())==$.trim($("#J_Password").val()))){
					msgOk(msgPwd,"");					
					
		}
		return true;
	});
	$('#J_Nick').blur(function(){
		var nick = $('#J_Nick').val();
		if($.trim(nick)=='') {
			var msgNick = $("#J_MsgInfoForm");
			msgInfo(msgNick,MSG_NICK_INFO);
			$('#J_Nick').focus();
			return false;
		}
	});
	/**
	 * 检查nick作为登录名是否存在
	 */
	$("#J_BtnInfoForm").click(function(){
		
		var nick = $('#J_Nick').val();
		if($.trim(nick)=='') {
			var msgNick = $("#J_MsgInfoForm");
			msgInfo(msgNick,MSG_NICK_INFO);
			return false;
		}
		$.ajaxSetup({
			dataType : 'json'//返回json
		});
		$.post("/user/check_nick.do",{nick:nick},function(result){
			var msgNick = $("#J_MsgInfoForm");

			if(!result.success){//存在
				msgErr(msgNick,MSG_NICK_EXIST);
			}else{//不存在，就注册
				msgOk(msgNick,MSG_NICK_OK);
				$('#J_InfoForm').attr("action","/user/reg.do").submit();
			}
		});
		return false;
	});

});
/**
 * 定时器 60秒内禁止发送短信验证码
 * @param timeOut
 */
function smsCodeInputDisable(timeOut){
	var obj = $('#J_BtnMobileCode');
	if(timeOut>0){
		obj.attr('disabled','true');
		obj.addClass('btn-disabled');
		obj.text(timeOut+"秒后可重新操作");	
		timerId = setTimeout(function(){smsCodeInputDisable(timeOut -1);},1000);
	}else{
		obj.removeAttr("disabled");
		obj.removeClass('btn-disabled');
		obj.text("免费获取校验码");
		
	}
}
/**
 * 更换图片验证码
 */
function changeImageCode(){
    var imgCheckcode = $('#J_CheckCodeImg1'); 
    var imgCheckcodeSrc = imgCheckcode.attr('src');
    var get_img_do_len = imgCheckcodeSrc.indexOf('?')+1;
    var get_img_do;
    if(get_img_do_len>0){
    	get_img_do = imgCheckcodeSrc.substring(0,get_img_do_len);
    }else{
    	get_img_do=imgCheckcodeSrc.substring(0,imgCheckcodeSrc.length);
    }
    var timestamp = (new Date()).valueOf(); 
    imgCheckcode.attr('src', get_img_do + '?t=' + timestamp);
}
/**
 * 校验mobileForm表单输入项格式是否正确
 * @returns {Boolean}
 */
function checkMobileForm(){
	if(!checkMobileNumber())
		return false;
	var imageCode = $('#J_CheckCodeInput').val();
	if(!checkCodeInput(imageCode))
		return false;

	return true;
}
/**
 * 检查手机号码格式
 * @returns {Boolean}
 */
function checkMobileNumber(){
	var mobileNumber = $("#J_Mobile").val();
	var msgMobile = $('#J_MsgMobile');
	if(mobileNumber==null||mobileNumber==''){
		msgErr(msgMobile,MSG_MOBILE_INFO);
		return false;
	}
		
	//判断mobileNumber是否合法手机号码
	var reg=/^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;    
	if (!reg.exec(mobileNumber)) {
		msgErr(msgMobile,MSG_MOBILE_FORMAT_ERROR);
		return false;
	}
	msgOk(msgMobile,MSG_MOBILE_OK);
	return true;
}
/**
 * 检查图片验证码格式
 * @param imageCode
 * @returns {Boolean}
 */
function checkCodeInput(imageCode){

	var msgCheckCode = $('#J_MsgCheckCode');
	//console.log(imageCode);
	if(imageCode==null||imageCode==''){
		msgErr(msgCheckCode,MSG_IMAGE_CHECK_CODE_INFO);
		return false;
	}
	var reg=/^[A-Za-z0-9]{4}$/;
	if (!reg.exec(imageCode)) {
		msgErr(msgCheckCode,MSG_IMAGE_CHECK_CODE_INVALID);
		return false;
	}
	return true;
}
/**
 * 校验图片验证码
 * @param imageCode
 * @returns {Boolean}
 */
function ajaxCheckImageCode(imageCode){
	if(!checkCodeInput(imageCode)) return false;
	$.ajaxSetup({
		dataType : 'json'//返回json
	});
	$.post("/user/checkcode/check_image_check_code.do",{imagecode:imageCode},function(result){
		//alert(result);
		var msgCheckCode = $('#J_MsgCheckCode');
		if(!result.success){//验证失败
			msgErr(msgCheckCode,MSG_IMAGE_CHECK_CODE_INVALID);
			changeImageCode();
			
		}else{//验证成功
			msgOk(msgCheckCode,MSG_IMAGE_CHECK_CODE_OK);
			
		}			
	});
}
/**
 * 发送短信验证码
 */
function sendSmsCode(){
	
	$.ajaxSetup({  
        dataType : 'json'
    }); 
	$.post("/user/checkcode/send_cell_checkcode.do",{mobile:$("#J_Mobile").val()},function(result){
		
		var msgResendCode = $('#J_MsgResendCode');

    	if(result.success){
    		$.post("/user/checkcode/sms_up_or_down.do",null,function(result){
    			if(result.type==2){
    				msgOk(msgResendCode,MSG_SEND_SMS_INFO);
     			}else{
    				msgErr(msgResendCode,MSG_SEND_SMS_GENERAL_ERROR);
   	 
    			}
    		});    		
    	}else{
    		msgErr(msgResendCode,MSG_SEND_SMS_GENERAL_ERROR);   		
    	}
	});
}
/**
 * 检查短信验证码格式
 * @returns {Boolean}
 */
function checkMobileCode(){
	var mobileCode = $("#J_MobileCode").val();
	var msgResendCode = $('#J_MsgResendCode');
	if(mobileCode==null||mobileCode==''){
		msgErr(msgResendCode,MSG_CELL_CHECK_CODE_INFO);
		return false;
	}
	//判断mobileCode是否6位数字
	var reg=/^\d{6}$/;    
	if (!reg.exec(mobileCode)) {
		msgErr(msgResendCode,MSG_CELL_CHECK_CODE_FORMAT_ERROR);
		return false;
	}

	return true;
}
/**
 * 检查email格式
 * @returns {Boolean}
 */
function checkEmail(){
	var email = $("#J_Email").val();
	var msgEmail = $('#J_MsgEmail');
	if(email==null||email==''){
		msgErr(msgEmail,MSG_EMAIL_INFO);
		return false;
	}
		
	//判断是否合法email
	var reg=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;    
	if (!reg.exec(email)) {
		msgErr(msgEmail,MSG_EMAIL_FORMAT_ERROR);
		return false;
	}
	msgOk(msgEmail,MSG_EMAIL_OK);

	return true;
}
/**
 * 显示err信息
 * @param obj
 * @param msg
 */
function msgErr( obj, msg){

	obj.removeClass('msg-type-ok');
	obj.removeClass('msg-type-info');
	obj.addClass('msg-type-error msg-display-inline');
	obj.css('display','');
	obj.children('.iconfont').html('󰅕');
	obj.children('.msg-cnt').html(msg);
}
/**
 * 显示ok信息
 * @param obj
 * @param msg
 */
function msgOk( obj, msg){
	obj.removeClass('msg-type-error');
	obj.removeClass('msg-type-info');
	obj.addClass('msg-type-ok msg-display-inline');
	obj.css('display','');
	obj.children('.iconfont').html('󰅖');
	obj.children('.msg-cnt').html(msg);	
}
/**
 * 显示info信息
 * @param obj
 * @param msg
 */
function msgInfo( obj, msg){
	obj.removeClass('msg-type-error');
	obj.removeClass('msg-type-ok');
	obj.addClass('msg-type-info');
	obj.css('display','');
	obj.children('.iconfont').html('󰅂');
	obj.children('.msg-cnt').html(msg);	
}