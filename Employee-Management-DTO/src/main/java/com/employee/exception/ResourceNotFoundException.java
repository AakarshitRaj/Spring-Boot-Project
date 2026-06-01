package com.employee.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message) {
        super(message);//super(message)->Sends message to parent exception class.
    }

}
