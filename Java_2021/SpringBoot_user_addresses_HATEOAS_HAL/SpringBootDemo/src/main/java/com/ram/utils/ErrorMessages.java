package com.ram.utils;

public enum ErrorMessages
{
	MISSING_REQUIRED_FIELDS(
			"Missing required fields, Please check the documentation for required fields"), 
	RECORD_ALREADY_EXIST("Record already exists"), 
	INTERNAL_SERVER_ERROR("Internal Server Error"), 
	RECORD_NOT_FOUND("Record with provided id is not found"), 
	AUTHENTICATION_FAILED("Authentication failed"), 
	COUND_NOT_UPDATE_RECORD("Could not update the Record"), 
	COUND_NOT_DELETE_RECORD("Could not delete the Record"), 
	EMAIL_ADDRESS_NOT_VERIFIED("Email Address Could not be Verified");

	private String errorMessage;

	ErrorMessages(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

}
