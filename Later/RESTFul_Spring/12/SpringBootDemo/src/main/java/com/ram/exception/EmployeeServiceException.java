package com.ram.exception;

public class EmployeeServiceException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public EmployeeServiceException(String message)
	{
		super(message);
	}

}
