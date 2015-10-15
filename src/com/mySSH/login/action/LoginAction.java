package com.mySSH.login.action;

import javax.annotation.Resource;

import com.mySSH.base.exception.NoUserException;
import com.mySSH.login.service.ILoginSer;
import com.mySSH.login.vo.UserVo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven{
	@Resource(name="loginService")
	private ILoginSer loginService;
	
	private UserVo userVo = new UserVo();
	
	public String execute(){
		try{
			if(loginService.login(userVo.getUsername(), userVo.getPassword())) return "success";
			else return "fail";
		}catch(NoUserException e){
			e.printStackTrace();
			return "noUser";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	public String register(){
		try{
			if(!userVo.getPassword().equals(userVo.getPassword2())){
				return "fail";
			}else{
				if(loginService.saveUser(userVo.getUsername(), userVo.getPassword())) return "success";
				else return "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public Object getModel() {
		return userVo;
	}
	
	
}
