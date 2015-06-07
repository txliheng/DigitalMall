package edu.ccu.comp.se.digitalmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ccu.comp.se.commons.utils.MD5;
import edu.ccu.comp.se.digitalmall.dao.IUserDao;
import edu.ccu.comp.se.digitalmall.model.User;
import edu.ccu.comp.se.digitalmall.service.IUserService;

@Service

public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public boolean login(String username,String password){
		
		return userDao.login(username, MD5.MD5Encode(password))>0?true:false;

	}


	@Override
	public boolean existUser(String userName) {
		
		return userDao.findByUsername(userName)>0?true:false;

	}
	
	@Override
	@Transactional
	public boolean reg(User user) {
		
		return this.userDao.save(user)>0?true:false;

	}

	@Override
	public boolean existNick(String nick) {
		return userDao.findByNick(nick)>0?true:false;
	}


	@Override
	public User find(String username,String password) {
		
		return userDao.findUser(username,MD5.MD5Encode(password));
	}
}
