package edu.ccu.comp.se.digitalmall.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ccu.comp.se.digitalmall.dao.IEmailInfoDao;
import edu.ccu.comp.se.digitalmall.model.EmailInfo;
@Repository
public class HibernateEmailInfoDao implements IEmailInfoDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public int save(EmailInfo emailInfo) {
		String result = this.hibernateTemplate.save(emailInfo).toString();
		if(result!=null){
			return Integer.parseInt(result);
		}
		return 0;
	}
	private static final String FIND_BY_VALIDATE_CODE_STATEMENT="select e from EmailInfo e where e.validateCode=:validate_code";
	@Override
	public EmailInfo findByValidateCode(String validateCode) {
	
		@SuppressWarnings("unchecked")
		List<EmailInfo> emailList=this.hibernateTemplate.findByNamedParam(FIND_BY_VALIDATE_CODE_STATEMENT, new String[]{"validate_code"}, new Object[]{validateCode});
		if(emailList.size()>0)
		
			return (EmailInfo) emailList.get(0);
		else 
			return null;

	}
	@Override
	public void update(EmailInfo emailInfo) {
		this.hibernateTemplate.update(emailInfo);
		
	}

}
