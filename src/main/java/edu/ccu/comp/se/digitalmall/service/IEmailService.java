package edu.ccu.comp.se.digitalmall.service;

import javax.mail.MessagingException;

import edu.ccu.comp.se.digitalmall.model.EmailInfo;


public interface IEmailService {

	public void  sendEmailWithTemplate(EmailInfo emailInfo) throws MessagingException;
	public EmailInfo findByValidateCode(String validateCode);
	public void save(EmailInfo emailInfo);
	public void update(EmailInfo emailInfo);
}
