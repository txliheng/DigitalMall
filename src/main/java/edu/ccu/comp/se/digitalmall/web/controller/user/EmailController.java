package edu.ccu.comp.se.digitalmall.web.controller.user;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ccu.comp.se.digitalmall.RegisterMessage;
import edu.ccu.comp.se.digitalmall.model.EmailInfo;
import edu.ccu.comp.se.digitalmall.service.IEmailService;
import edu.ccu.comp.se.digitalmall.web.listener.RegisterEmailSessionListener;


@Controller
@RequestMapping("/user")
public class EmailController {

    private static final String subject="新用户确认通知信";    
    private static final String from = "jerome610@163.com";
    private static final String emailTemplateName = "emailtemplates/confirmEmail.vm";
    @Autowired
	private IEmailService emailService;
 
	/**
	 * 发送用户注册确认信
	 * @param mobile
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/send_email.do",method = RequestMethod.POST)
	public String send_email_check_code(@RequestParam(value="email",required=true)final String email,HttpServletRequest request) throws Exception{

			final String activate_code = UUID.randomUUID().toString().replaceAll("-", "");
			final HttpSession session = request.getSession();
			session.setAttribute(RegisterMessage.EMAIL, email);
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.DAY_OF_MONTH,1);
			final Date validate_date = (Date) calendar.getTime();
			
			final StringBuffer sb=new StringBuffer();    
			sb.append("http://localhost/user/register_confirm.html?activateCode=");    
			sb.append(activate_code);    
		    
			//发送email
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						EmailInfo emailInfo = new EmailInfo();
						emailInfo.setEmail(email);
						emailInfo.setValidateCode(activate_code);
						emailInfo.setValidateDate(validate_date);
						emailInfo.setStatus(Boolean.FALSE);
						emailInfo.setFrom(from);
						emailInfo.setTo(new String[] { email });
						emailInfo.setSubject(subject);
						emailInfo.setEmailPlaceHolder(sb.toString());
						emailInfo.setTempleteName(emailTemplateName);
						
						session.setAttribute("REGISTER_EMAIL_LISTENER", new RegisterEmailSessionListener(session,"EMAIL_INFO",emailInfo,emailService));
						emailService.sendEmailWithTemplate(emailInfo);
						session.removeAttribute("REGISTER_EMAIL_LISTENER");//触发listener的valueUnbound方法
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}).start();
			
			return "redirect:/user/reg/email_sent.html";
	}
	/**
	 * 点击注册确认链接
	 * @param activateCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/register_confirm.html",method=RequestMethod.GET)  
	protected String activate(@RequestParam(value="activateCode",required=true) String activateCode, HttpServletRequest request){ 
		HttpSession session = request.getSession();
		EmailInfo emailInfo=emailService.findByValidateCode(activateCode);
		if(emailInfo!=null){
			String validate_code=emailInfo.getValidateCode();
			String email=emailInfo.getEmail();
			Date validate_time=emailInfo.getValidateDate();
			Boolean status = emailInfo.getStatus();
			if(status!=null&&status.booleanValue()){
				session.setAttribute(RegisterMessage.REGISTER_MESSAGE, "您已激活，您的链接已失效");
				return "redirect:/user/reg/registerCode_invalidate.html";
			}else{
				if(validate_time.after(new Date())){
					if(activateCode.equals(validate_code)){
						session.setAttribute("LOGIN_NAME", email);
						session.setAttribute(RegisterMessage.EMAIL, email);
						emailInfo.setStatus(Boolean.TRUE);
						emailService.update(emailInfo);
						return "redirect:/user/reg/fill_user_info.html";
					}else{
						session.setAttribute(RegisterMessage.REGISTER_MESSAGE, "激活码错误");
						return "redirect:/user/reg/registerCode_err.html";
					}
				}else{				
					session.setAttribute(RegisterMessage.REGISTER_MESSAGE,"您的链接已过期");
					return "redirect:/user/reg/over_time.html";
				}
			}
		}else{
			session.setAttribute(RegisterMessage.REGISTER_MESSAGE, "您还未注册，请先注册");
			return "redirect:/user/reg/registerCode_err.html";

		}		
	}		
}
