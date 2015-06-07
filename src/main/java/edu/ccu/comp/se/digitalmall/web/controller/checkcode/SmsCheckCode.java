package edu.ccu.comp.se.digitalmall.web.controller.checkcode;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ccu.comp.se.digitalmall.service.ISmsCodeService;

@Controller
@RequestMapping("/user/checkcode")
//@Scope("prototype")//多例
//@Scope("singleton")//默认是单例
public class SmsCheckCode {

    private static final String MOBILE_CODE = "MOBILE_CODE";  
    private static final String VALIDATE_CODE_TIME = "VALIDATE_CODE_TIME";  
	@Autowired
	private ISmsCodeService smsCodeComponent;
	private String sendSmsStatus;
	private SendSmsTask sendSmsTask;
	//private static Logger logger = Logger.getLogger(SmsCheckCode.class);
	/**
	 * 发送短信验证码
	 * @param mobile
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/send_cell_checkcode.do",method = RequestMethod.POST)
	@ResponseBody
	public String send_cell_checkcode(@RequestParam(value="mobile",required=true)String mobile,HttpServletRequest request) throws Exception{
		try {
			int mobile_code = (int)((Math.random()*9+1)*100000);//6位短信验证码

		    String smsText = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。"); 

		    System.out.println(mobile_code);
		    //logger.debug(mobile_code);
		    sendSmsTask=new SendSmsTask(mobile,smsText);
		    new Thread(sendSmsTask).start();
		    
			HttpSession session = request.getSession();
			session.setAttribute(		
					MOBILE_CODE,				
					String.valueOf(mobile_code));
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.MINUTE,15);
			Date date = (Date) calendar.getTime();
			session.setAttribute(VALIDATE_CODE_TIME, date);
			return "{\"success\":true}";
		} catch (Exception e) {	
			return "{\"success\":false}";
		}

	}
	/**
	 * 运营商返回发送短信状态代码 
	 * 状态码如果是2，表示发送成功
	 * @return
	 */
	@RequestMapping(value="/sms_up_or_down.do",method = RequestMethod.POST)
	@ResponseBody
	public String sms_up_or_down(){
		StringBuilder result = new StringBuilder();
		if(sendSmsStatus!=null&&sendSmsStatus!=""){
			result.append("{\"type\":"+sendSmsStatus+","+"\"success\":");
			if("2".equals(sendSmsStatus)){
				
				result.append("true}");	
				
			}else{
				result.append("false}");
			}
		}
		return result.toString();
	}
	/**
	 * 校验短信验证码
	 * @param smsCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/check_cell_checkcode.do",method = RequestMethod.POST)
	@ResponseBody
	public String check_cell_checkcode(@RequestParam(value="smscode",required=true)String smsCode,HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		String mobileCode = (String) session.getAttribute(MOBILE_CODE);
		Date validate_code_time = (Date) session.getAttribute(VALIDATE_CODE_TIME);
		String result="{\"success\":";

		if(smsCode!=null&&!"".equals(smsCode)&&smsCode.equals(mobileCode)&&validate_code_time.after(new Date())){
			return result+"true}";
		}else{
			return result+"false}";
		}
		
	}
	private final class SendSmsTask implements Runnable {

		private String mobile;
		private String smsText;
	
		public SendSmsTask(String mobile,String smsText){
			this.mobile = mobile;
			this.smsText = smsText;
		}
		
		public void run() {
			try {
				sendSmsStatus = smsCodeComponent.sendSmsCode(mobile,smsText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
