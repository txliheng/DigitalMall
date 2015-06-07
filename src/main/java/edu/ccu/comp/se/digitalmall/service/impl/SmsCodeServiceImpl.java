package edu.ccu.comp.se.digitalmall.service.impl;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import edu.ccu.comp.se.commons.utils.MD5;
import edu.ccu.comp.se.digitalmall.service.ISmsCodeService;



@Service
public class SmsCodeServiceImpl implements ISmsCodeService {
	private static final String SMS_SEND_URI = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private static final String ACCOUNT="cf_xxxlll";
	private static final String PASSWORD="xxxlll123456";
	@Override
	public String sendSmsCode(String mobile,String content) throws HttpException, IOException, DocumentException {
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(SMS_SEND_URI); 
			
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", ACCOUNT), 
			    //new NameValuePair("password", PASSWORD), //密码可以使用明文密码或使用32位MD5加密
			    new NameValuePair("password", MD5.MD5Encode(PASSWORD)),
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);
		client.executeMethod(method);	
		String SubmitResult =method.getResponseBodyAsString();
		Document doc = DocumentHelper.parseText(SubmitResult); 
		Element root = doc.getRootElement();
		String code = root.elementText("code");	
		if("2" == code ){
			System.out.println("短信提交成功");
		}
		return code;


	}

}
