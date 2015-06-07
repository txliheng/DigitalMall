package edu.ccu.comp.se.digitalmall.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.dom4j.DocumentException;

public interface ISmsCodeService {


	public String sendSmsCode(String mobile, String smsText) throws HttpException, IOException, DocumentException;

}
