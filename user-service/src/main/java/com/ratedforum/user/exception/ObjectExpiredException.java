package com.ratedforum.user.exception;

public class ObjectExpiredException extends Exception{

	private static final long serialVersionUID = -336460788001760353L;
	
	public ObjectExpiredException(String message){
		super(message);
	}
}
