package edu.ccu.comp.se.digitalmall.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import edu.ccu.comp.se.digitalmall.model.EmailInfo;
import edu.ccu.comp.se.digitalmall.service.IEmailService;


//@WebListener
public class RegisterEmailSessionListener extends HttpSessionBindingEvent implements HttpSessionBindingListener {

    private static final long serialVersionUID = -6903675057691963894L;

	private EmailInfo emailInfo;	
	private IEmailService emailService;


    /**
     * @see HttpSessionBindingEvent#HttpSessionBindingEvent(HttpSession, String)
     */
    public RegisterEmailSessionListener(HttpSession session, String name) {
        super(session, name);
    }
       
    /**
     * @see HttpSessionBindingEvent#HttpSessionBindingEvent(HttpSession, String, Object)
     */
    public RegisterEmailSessionListener(HttpSession session, String name, Object value,IEmailService emailService) {
        super(session, name, value);
        this.emailInfo = (EmailInfo) value;
        this.emailService = emailService;

    }
    

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	this.emailService.save(emailInfo);

    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
  
    }
	
}
