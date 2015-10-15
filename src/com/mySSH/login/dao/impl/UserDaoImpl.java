package com.mySSH.login.dao.impl;

import org.springframework.stereotype.Component;

import com.mySSH.base.dao.impl.BaseDaoImpl;
import com.mySSH.login.dao.IUserDao;
import com.mySSH.login.model.User;

@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
	
	public UserDaoImpl(){
		super(User.class);
	}
	
}	
