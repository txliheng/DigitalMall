package edu.ccu.comp.se.digitalmall.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ccu.comp.se.digitalmall.RegisterMessage;

/**
 * 用户注册界面
 * @author Administrator
 *
 */
@Controller
@SessionAttributes(
	{RegisterMessage.REGISTER_MESSAGE,
	RegisterMessage.MOBILE_NUMBER,
	RegisterMessage.EMAIL,
	RegisterMessage.LOGIN_NAME,
	RegisterMessage.NICK})

@RequestMapping("/user/reg")
public class RegUIController {

    @RequestMapping(value="/fill_mobile.html",method=RequestMethod.GET)
    public String fill_mobile(ModelMap model){
    	
    	model.addAttribute("title", "账号注册");
    	model.addAttribute("titleAffix", "数码城");
       
    	return "home/page/user/reg/fill_mobile";  
  
    }
    
    @RequestMapping(value="/mobile_used.html",method=RequestMethod.GET)
    public String mobileUsed(@ModelAttribute(RegisterMessage.MOBILE_NUMBER)String mobile,ModelMap model  ){
        model.addAttribute("title", "账号注册");
        model.addAttribute("titleAffix", "数码城");
        model.addAttribute("mobile", mobile);
        return "home/page/user/reg/mobile_used";       
    }
    
    @RequestMapping(value="/fill_email.html",method=RequestMethod.GET)
    public String fillEmail(ModelMap model){
    	model.addAttribute("title", "账号注册");
    	model.addAttribute("titleAffix", "数码城");
        return "home/page/user/reg/fill_email";       
    }
    @RequestMapping(value="/email_sent.html",method=RequestMethod.GET)
    public String emailSent(@ModelAttribute(RegisterMessage.EMAIL)String email,ModelMap model){
        model.addAttribute("title", "账号注册");
        model.addAttribute("titleAffix", "数码城");
        model.addAttribute("email", email);
        return "home/page/user/reg/email_sent";       
    }
    @RequestMapping(value="/fill_user_info.html",method=RequestMethod.GET)
    public String fillUserInfo(
    		@ModelAttribute(RegisterMessage.LOGIN_NAME)String loginName,
    		ModelMap model){
    	model.addAttribute("title", "账号注册");
    	model.addAttribute("titleAffix", "数码城");
    	model.addAttribute("loginName",loginName);
        if(loginName!=null&&!"".equals(loginName))
        	return "home/page/user/reg/fill_user_info"; 
        else
        	return "home/page/user/reg/fill_mobile"; 
    }
    @RequestMapping(value="/reg_success.html",method=RequestMethod.GET)
    public String reg_success(
    		@ModelAttribute(RegisterMessage.LOGIN_NAME)String loginName,
    		HttpServletRequest request,
    		@ModelAttribute(RegisterMessage.NICK)String nick,
    		ModelMap model){
    	String mobile = (String) request.getSession().getAttribute(RegisterMessage.MOBILE_NUMBER);
    	String email = (String) request.getSession().getAttribute(RegisterMessage.EMAIL);
    	model.addAttribute("title", "账号注册");
    	model.addAttribute("titleAffix", "数码城");
    	model.addAttribute("loginName",loginName);
    	model.addAttribute("mobile", mobile);
    	model.addAttribute("email", email);
    	model.addAttribute("nick", nick);
        return "home/page/user/reg/reg_success";       
    }

    @RequestMapping(
    		value={"/reg_fail.html","/over_time.html",
    				"/registerCode_err.html",
    				"/registerCode_invalidate.html"},
    				method=RequestMethod.GET)
    public String reg_fail(ModelMap model){
        model.addAttribute("title", "账号注册");
        model.addAttribute("titleAffix", "数码城");
        return "home/page/user/reg/reg_fail";
    }

}
