package edu.ccu.comp.se.digitalmall.web.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/")
    public String index(ModelMap modelMap,HttpServletRequest request) {
    	
    	modelMap.addAttribute("title", "欢迎访问数码城");
    	modelMap.addAttribute("titleAffix", "All In");
    	String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		modelMap.addAttribute("nick",nick );
    	return "home/page/index";     

    }
}
