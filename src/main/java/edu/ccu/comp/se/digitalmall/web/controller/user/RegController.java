package edu.ccu.comp.se.digitalmall.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import edu.ccu.comp.se.commons.utils.MD5;
import edu.ccu.comp.se.digitalmall.RegisterMessage;
import edu.ccu.comp.se.digitalmall.model.User;
import edu.ccu.comp.se.digitalmall.service.IUserService;


@Controller
@SessionAttributes({
	RegisterMessage.REGISTER_MESSAGE,
	RegisterMessage.MOBILE_NUMBER,
	RegisterMessage.EMAIL,
	RegisterMessage.LOGIN_NAME,
	RegisterMessage.NICK})

@RequestMapping(value="/user")
public class RegController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/reg.do",method=RequestMethod.POST)
	public String reg(
			@RequestParam(value="nick",required=true)String nick,
			@RequestParam(value="pwd",required=true)String pwd,			
			HttpServletRequest request,
			RedirectAttributesModelMap  model){
		
		String userName = (String) request.getSession().getAttribute(RegisterMessage.LOGIN_NAME);
		String email = (String) request.getSession().getAttribute(RegisterMessage.EMAIL);
		String mobile =  (String) request.getSession().getAttribute(RegisterMessage.MOBILE_NUMBER);
		if(userName!=null&&(mobile!=null||email!=null)){
			if(this.userService.existUser(userName)){
				model.addFlashAttribute(RegisterMessage.REGISTER_MESSAGE,"登录名 "+userName+" 已存在,请重新注册");
				return "redirect:/user/reg/reg_fail.html";
			}
			User user = new User();
			user.setPassword(MD5.MD5Encode(pwd));
			user.setMobile(mobile);
			user.setEmail(email);
			user.setNick(nick);
			try {
				if(this.userService.reg(user)){
					model.addFlashAttribute(RegisterMessage.NICK, nick);
					return "redirect:/user/reg/reg_success.html";
				}else{
					model.addFlashAttribute(RegisterMessage.REGISTER_MESSAGE,"注册失败，请重新注册");	
					return "redirect:/user/reg/reg_fail.html";
				}				
			} catch (Exception e) {
				model.addFlashAttribute(RegisterMessage.REGISTER_MESSAGE,"注册失败，请重新注册");	
				return "redirect:/user/reg/reg_fail.html";
			}

		}else{
			model.addFlashAttribute(RegisterMessage.REGISTER_MESSAGE,"登录名或电话号码或电子邮箱 "+userName+" 不能为空,请重新注册");
			return "redirect:/user/reg/reg_fail.html";
		}
		
	}
	
	@RequestMapping(value="/check_cell.do",method=RequestMethod.POST)
	public RedirectView check_cell(
			@RequestParam(value="mobile",required=true)	String mobileNumber,
			ModelMap model){

		model.addAttribute(RegisterMessage.MOBILE_NUMBER, mobileNumber);
		String viewName;
		
       if(userService.existUser(mobileNumber)){
    	   viewName="/user/reg/mobile_used.html";        	
       }else{
    	   model.addAttribute(RegisterMessage.LOGIN_NAME, mobileNumber);
    	   viewName="/user/reg/fill_user_info.html";
        }
		RedirectView view = new RedirectView(viewName);
		view.setExposeModelAttributes(false);
		return view;
	}
	@RequestMapping(value="/check_email.do",method=RequestMethod.POST)
	@ResponseBody
	public String check_email(
			@RequestParam(value="email",required=true)String email,
			RedirectAttributesModelMap model){
		
		String result="{\"success\":";
		model.addFlashAttribute(RegisterMessage.EMAIL, email);
		if(!userService.existUser(email)){
			return result+"true}";	        
		}else{
			return  result+"false}";  
		}			
	}
	@RequestMapping(value="/check_nick.do",method=RequestMethod.POST)
	@ResponseBody
	public String check_nick(
			@RequestParam(value="nick",required=true)String nick){

		String result="{\"success\":";
		if(!userService.existNick(nick)){
			return result+"true}";
		}else{
			return  result+"false}";
		}			
	}
	
}
