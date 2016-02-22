package com.ratedforum.user.exception;

public class MissingParametersException extends Exception{

	private static final long serialVersionUID = -336460788001760353L;
	
	public MissingParametersException(String message){
		super(message);
	}
}
