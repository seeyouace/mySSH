package com.mySSH.base.exception;

public class NoUserException extends RuntimeException{
	
	public NoUserException(){
		super();
	}
	
	public NoUserException(String message){
		super(message);
	}
}
