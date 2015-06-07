package edu.ccu.comp.se.digitalmall.dao;

import edu.ccu.comp.se.digitalmall.model.User;

public interface IUserDao {

	public abstract int login(String username, String password);
	public abstract int findByNick(String nick);
	public abstract int findByUsername(String username);
	public abstract int save(User user);
	public abstract User findUser(String username,String password);

}