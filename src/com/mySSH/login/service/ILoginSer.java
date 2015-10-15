package com.mySSH.login.service;

public interface ILoginSer {
	public boolean login(String username,String password) throws Exception;
	public boolean saveUser(String username,String password) throws Exception;
}
