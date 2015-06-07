package edu.ccu.comp.se.digitalmall.web.controller.checkcode;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octo.captcha.service.image.ImageCaptchaService;

import edu.ccu.comp.se.commons.ImageCodeConstants;
import edu.ccu.comp.se.commons.web.jcaptcha.JCImageCaptchaService;
import edu.ccu.comp.se.commons.web.jcaptcha.WordMap;

@Controller
@RequestMapping("/user/checkcode")
public class ImageCheckCode {
	private static ImageCaptchaService imageCaptchaService = JCImageCaptchaService
			.getInstance();


	@RequestMapping("/get_image_check_code.do")
	public void get_image_check_code(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		response.setHeader("Cache-Control", "no-store");//http 1.1
//		response.setHeader("Pragma", "no-cache");//http1.0,1.1
//		response.setDateHeader("Expires", 0);//0表示立即过期
		response.setContentType("image/jpeg");
		String sessionId = request.getSession().getId();
		
		BufferedImage bufferedImage = imageCaptchaService
				.getImageChallengeForID(sessionId);

		ServletOutputStream servletOutputStream = response.getOutputStream();
		ImageIO.write(bufferedImage, "jpg", servletOutputStream);
		request.getSession().setAttribute(
				ImageCodeConstants.IMAGE_CODE_GENERATED_WORD,
				WordMap.getWordsMap().get(sessionId));
		try {
			servletOutputStream.flush();
		} finally {
			servletOutputStream.close();
		}

	}
	@RequestMapping("/check_image_check_code.do")
	@ResponseBody
	public String check_image_check_code(@RequestParam(value="imagecode",required=true)String imageCode,HttpServletRequest request) {
		String generatedWord = (String) request.getSession().getAttribute(ImageCodeConstants.IMAGE_CODE_GENERATED_WORD);
		String result="{\"success\":";
		if(imageCode!=null&&!"".equals(imageCode)&&imageCode.equals(generatedWord)){
			return result+"true}";
		}else{
			return result+"false}";
		}		
	}
}
