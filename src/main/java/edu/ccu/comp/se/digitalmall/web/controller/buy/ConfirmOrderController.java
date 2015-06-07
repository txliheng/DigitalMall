package edu.ccu.comp.se.digitalmall.web.controller.buy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;

@Controller
@RequestMapping(value="/buy")
public class ConfirmOrderController {

	@RequestMapping(value="/order/confirm_order.html")
	public String confirmOrder(
			HttpServletRequest request,
			ModelMap model){
		model.addAttribute("title", "确认订单");
		model.addAttribute("titleAffix", "数码城-All In");
		String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		model.addAttribute("nick",nick );

		return "home/page/buy/order/confirm_order";
	}
	@RequestMapping(value="/confirm_order.html")
	public String multiFormSubmit(ModelMap model){
		//提交订单
		//如果成功，转入付款页面
		return "redirect:/payment/cashier.html";
	}
}
