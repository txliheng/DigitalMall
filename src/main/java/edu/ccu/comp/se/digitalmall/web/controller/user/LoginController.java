package edu.ccu.comp.se.digitalmall.web.controller.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;
import edu.ccu.comp.se.digitalmall.model.User;
import edu.ccu.comp.se.digitalmall.service.IUserService;
@Controller
@RequestMapping("/user")
public class LoginController {
    /**
     * 用户登录验证
     * @return
     */
	@Autowired
	IUserService userService;
	@RequestMapping("/login.html")
	public String loginUI(
			ModelMap model,
			@RequestParam(value="username",required=false)String username,
    		@RequestParam(value="password",required=false)String password,
			@RequestParam(value="redirectURL",required=false)String redirectUrl,
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{

		if(username!=null&&!"".equals(username.trim())&&
		   password!=null&&!"".equals(password.trim())){
			if(userService.existUser(username.trim())){
				User user=userService.find(username.trim(),password.trim());
		    	if(user!=null){
		    		request.getSession().setAttribute(LoginMessage.USER,user);
		    		WebUtil.addCookie(response, LoginMessage.NICK, user.getNick(),90*24*3600 /*request.getSession().getMaxInactiveInterval()*/);
		    		if(redirectUrl!=null&&!"".equals(redirectUrl)){
		    			model.remove("redirectURL");
		    			return "redirect:"+redirectUrl;
		    		}else{
		    			return "redirect:/";
		    		}
		    	}else{
		    		model.addAttribute("Message", "您输入的密码和账户名不匹配，请重新输入。");
		    	}
	    	}else{
	    		model.addAttribute("Message", "帐户名不存在！");
	    	}
		}
		model.addAttribute("title", "欢迎访问数码城");
		model.addAttribute("titleAffix", "All In");
		if(redirectUrl!=null&&!"".equals(redirectUrl)){
			//redirectUrl = new String(Base64.decodeBase64(redirectUrl.trim().getBytes()));//获取解码后的url
			redirectUrl = URLDecoder.decode(redirectUrl.trim(), "UTF-8");//获取解码后的url
			model.addAttribute("redirectURL",redirectUrl);
		}
		return "home/page/user/login/login";  
 
	}
	/**
	 * 用户退出系统
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout.html")
	public String logout(
			ModelMap model,
			HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(LoginMessage.USER);
		WebUtil.addCookie(response, LoginMessage.NICK, null, 0);
		return "redirect:/user/login.html";  
 
	}

}
