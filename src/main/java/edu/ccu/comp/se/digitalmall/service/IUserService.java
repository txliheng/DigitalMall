package edu.ccu.comp.se.digitalmall.service;

import edu.ccu.comp.se.digitalmall.model.User;

public interface IUserService {

	public abstract boolean login(String username, String password);

	public abstract boolean reg(User user);

	public abstract boolean existUser(String userName);

	public abstract boolean existNick(String nick);
	
	public User find(String username,String password);

}