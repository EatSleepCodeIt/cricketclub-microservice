package com.ratedforum.user.exception;

public class ObjectNotFoundException extends Exception{

	private static final long serialVersionUID = -336460788001760353L;
	
	public ObjectNotFoundException(String message){
		super(message);
	}
}
