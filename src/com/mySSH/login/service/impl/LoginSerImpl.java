package com.mySSH.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mySSH.base.exception.NoUserException;
import com.mySSH.login.dao.IUserDao;
import com.mySSH.login.model.User;
import com.mySSH.login.service.ILoginSer;

@Component("loginService")
public class LoginSerImpl implements ILoginSer{
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public boolean login(String username,String password){
		String hql = "select u from User u where u.username = '"+username+"'";
		User user = (User)userDao.findOneWithAny(hql);
		if(user == null){
			throw new NoUserException("user not exist!");
		}
		if(password.equals(user.getPassword())) return true;
		else return false;
	}
	
	public boolean saveUser(String username,String password) throws Exception{
		String hql = "select count(*) from User u where u.username = '"+username+"'";
		int count = (int)userDao.findCount(hql);
		if(count > 0){
			return false;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userDao.save(user);
		return true;
	}
}
