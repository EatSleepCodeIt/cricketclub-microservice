package com.ratedforum.user.exception;

public class PrincipalUserMismatchException extends Exception {

	private static final long serialVersionUID = -5464030706060936824L;

	public PrincipalUserMismatchException(String message) {
		super(message);
	}
}
