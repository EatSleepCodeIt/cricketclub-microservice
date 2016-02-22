package com.ratedforum.user.exception;

public class ObjectAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -336460788001760353L;
	
	public ObjectAlreadyExistsException(String message){
		super(message);
	}
}
