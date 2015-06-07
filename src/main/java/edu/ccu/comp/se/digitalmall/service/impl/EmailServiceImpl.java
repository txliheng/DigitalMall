package edu.ccu.comp.se.digitalmall.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import edu.ccu.comp.se.digitalmall.dao.IEmailInfoDao;
import edu.ccu.comp.se.digitalmall.model.EmailInfo;
import edu.ccu.comp.se.digitalmall.service.IEmailService;


@Service
public class EmailServiceImpl implements IEmailService {


	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private IEmailInfoDao emailInfoDao;

	public void sendEmailWithTemplate(EmailInfo emailInfo) throws MessagingException  {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		message.setFrom(emailInfo.getFrom());
		message.setTo(emailInfo.getTo());
		message.setSubject(emailInfo.getSubject());
		message.setSentDate(new Date());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("emailPlaceHolder", emailInfo.getEmailPlaceHolder());

		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailInfo.getTempleteName(),
				"UTF-8", model);
		message.setText(text, true);
		Resource img = new ClassPathResource("emailtemplates/conform_email_submit.png");
		message.addInline("confirm_email_submit", img);
		mailSender.send(mimeMessage);

	}

	@Override
	public EmailInfo findByValidateCode(String validateCode) {
		return emailInfoDao.findByValidateCode(validateCode);
		
	}

	@Override
	public void save(EmailInfo emailInfo) {
		emailInfoDao.save(emailInfo);
		
	}

	@Override
	public void update(EmailInfo emailInfo) {
		emailInfoDao.update(emailInfo);
		
	}


}
