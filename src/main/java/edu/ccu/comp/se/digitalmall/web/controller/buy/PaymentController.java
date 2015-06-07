package edu.ccu.comp.se.digitalmall.web.controller.buy;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ccu.comp.se.commons.utils.WebUtil;
import edu.ccu.comp.se.digitalmall.LoginMessage;
import edu.ccu.comp.se.digitalmall.utils.ConfigInfo;
import edu.ccu.comp.se.digitalmall.utils.PaymentUtil;

@Controller
@RequestMapping(value="/payment")
public class PaymentController {

	/**
	 * 选择支付银行界面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/cashier.html")
	public String cashier(ModelMap modelMap){
    	modelMap.addAttribute("title", "支付宝");
    	modelMap.addAttribute("titleAffix", "网上支付 安全快速");

		return "home/page/buy/payment/payment";
	}
	/**
	 * 向第三方支付网关(易宝支付)发起支付请求
	 * @param modelMap
	 * @param request
	 * @param p2_Order:订单号
	 * @param pd_FrpId:选择的支付银行
	 * @param p3_Amt:支付金额
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/bankCardForm.html")
	public String bankCardForm(
			ModelMap modelMap,
			HttpServletRequest request,
			@RequestParam(value="orderId",required=true)String p2_Order,
			@RequestParam(value="channelToken",required=true)String pd_FrpId,
			@RequestParam(value="realAmount",required=true)String p3_Amt) throws UnsupportedEncodingException{
		request.setCharacterEncoding("GBK");
		//如果你用易宝支付，你需要在易宝支付注册一个账号，同时提供给我们测试账号
		String p1_MerId = ConfigInfo.getValue("p1_MerId");//商户id
		String aKey = ConfigInfo.getValue("keyValue");//商户密钥
		String p8_Url = ConfigInfo.getValue("merchantCallbackURL");	//商户回调url
		String p0_Cmd = "Buy";//业务类型,在线支付固定为Buy
		String p4_Cur = "CNY";
		String p5_Pid = "";// 商品ID,不能写成null
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		String p9_SAF = "0";// 需要填写送货信息 0：不需要 1:需要
		String pa_MP = "";// 商家扩展信息
		String pr_NeedResponse = "0";// 应答机制
		String hmac = PaymentUtil.buildHmac
		(p0_Cmd,p1_MerId,p2_Order,p3_Amt,
		 p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,
		 p8_Url,p9_SAF,pa_MP,pd_FrpId,
		 pr_NeedResponse,aKey);
		modelMap.addAttribute("p0_Cmd", p0_Cmd);
		modelMap.addAttribute("p1_MerId", p1_MerId);
		modelMap.addAttribute("p2_Order", p2_Order);
		modelMap.addAttribute("p3_Amt", p3_Amt);
		modelMap.addAttribute("p4_Cur", p4_Cur);
		modelMap.addAttribute("p5_Pid", p5_Pid);
		modelMap.addAttribute("p6_Pcat", p6_Pcat);
		modelMap.addAttribute("p7_Pdesc", p7_Pdesc);
		modelMap.addAttribute("p8_Url", p8_Url);
		modelMap.addAttribute("p9_SAF", p9_SAF);
		modelMap.addAttribute("pa_MP", pa_MP);
		modelMap.addAttribute("pd_FrpId", pd_FrpId);
		modelMap.addAttribute("pr_NeedResponse", pr_NeedResponse);
		modelMap.addAttribute("hmac", hmac);
    	modelMap.addAttribute("title", "发起支付请求");
		return "home/page/buy/payment/connection";
	}
	/**
	 * 响应银行支付结果请求
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/yeepay/paymentResponse.html")
	public String paymentResponse(
			HttpServletRequest request,
			ModelMap model) throws UnsupportedEncodingException{
		request.setCharacterEncoding("GBK");
		String merchantID = ConfigInfo.getValue("p1_MerId"); // 商家ID
		String keyValue = ConfigInfo.getValue("keyValue"); // 商家密钥
		
		String sCmd = request.getParameter("r0_Cmd"); //业务类型
		String sResultCode = request.getParameter("r1_Code"); //扣款结果,该字段值为1时表示扣款成功.
		String sTrxId = request.getParameter("r2_TrxId"); //YeePay易宝交易订单号
		String amount = request.getParameter("r3_Amt");//扣款金额,交易结束后,YeePay易宝交易系统将实际扣款金额返回给商户
		String currency = request.getParameter("r4_Cur");//交易币种,人民币为CNY
		String itemId = request.getParameter("r5_Pid");//商品ID
		String orderId = request.getParameter("r6_Order");//商户订单号
		String userId = request.getParameter("r7_Uid");//YeePay易宝会员ID
		String mp  = request.getParameter("r8_MP");//商户扩展信息,可以任意填写1K 的字符串,交易返回时将原样返回
		String bType = request.getParameter("r9_BType");//交易结果通知类型,1: 交易成功回调(浏览器重定向)2: 交易成功主动通知(服务器点对点通讯)
		//String rb_BankId  = request.getParameter("rb_BankId");//支付银行
		//String rp_PayDate = request.getParameter("rp_PayDate");//在银行支付时的时间
		String hmac = request.getParameter("hmac");//MD5交易签名
		
		boolean result = PaymentUtil.verifyCallback(
		hmac, merchantID, sCmd, sResultCode, 
		sTrxId, amount,	currency, itemId, 
		orderId, userId, mp, bType, keyValue);
		if(result){
			if("1".equals(sResultCode)){
				//这个地方应该把数据库中订单的支付状态设置成已经支付.
				String message = "订单号为:"+ orderId+ "的订单支付成功了";
				message += ",用户支付了"+ amount +"元";
				message +=",交易结果通知类型:";
				if("1".equals(bType)){
					 message += "浏览器重定向";
				}else if("2".equals(bType)){
					 message += "易宝支付网关后台程序通知";
				}
				message += ",易宝订单系统中的订单号为:"+ sTrxId;
				model.addAttribute("message", message);
			}else{
				model.addAttribute("message", "用户支付失败");
			}
		}else{
			model.addAttribute("message", "数据来源不合法");
		}
		model.addAttribute("title", "支付结果");
    	String nick = WebUtil.getCookieByName(request, LoginMessage.NICK);
    	if(nick!=null&&!"".equals(nick))
    		model.addAttribute("nick",nick );

		return "home/page/buy/payment/response";
	}

}
