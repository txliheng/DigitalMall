package edu.ccu.comp.se.digitalmall.dao;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.ccu.comp.se.digitalmall.dao.IUserDao;
import edu.ccu.comp.se.digitalmall.model.User;

public class UserDaoTest {

	private static ApplicationContext ctx;
	private  static IUserDao userDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (IUserDao) ctx.getBean("hibernateUserDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setPassword("1234");
		user.setMobile("15526814996");
		user.setNick("jerome");
		
		int pk = userDao.save(user);
		System.out.println(pk);
		Assert.assertNotEquals(0,pk);
	}

}
