package edu.ccu.comp.se.digitalmall.dao;

import edu.ccu.comp.se.digitalmall.model.EmailInfo;

public interface IEmailInfoDao {

	public abstract int save(EmailInfo emailInfo);

	public abstract EmailInfo findByValidateCode(String validateCode);

	public abstract void update(EmailInfo emailInfo);
}
