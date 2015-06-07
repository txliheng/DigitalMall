package edu.ccu.comp.se.digitalmall.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import edu.ccu.comp.se.digitalmall.dao.IUserDao;
import edu.ccu.comp.se.digitalmall.model.User;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)

public class UserServiceTest  {


	@Autowired
	private IUserDao userDao;
	
	@Test
	@Transactional
	public void testReg() {

		User user = new User();
		user.setPassword("1234");
		user.setMobile("15526814996");
		user.setNick("jerome");
		int pk = userDao.save(user);
		Assert.assertNotEquals(0,pk);
	}

}
