package com.mySSH.login.dao.impl;

import org.springframework.stereotype.Component;

import com.mySSH.base.dao.impl.BaseDaoImpl;
import com.mySSH.login.dao.ILoginHistoryDao;
import com.mySSH.login.model.LoginHistory;

@Component("loginHistoryDao")
public class LoginHistoryDaoImpl extends BaseDaoImpl<LoginHistory> implements ILoginHistoryDao{
	
	public LoginHistoryDaoImpl(){
		super(LoginHistory.class);
	}
	
}	
