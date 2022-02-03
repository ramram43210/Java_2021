package com.ram.model.response;

import java.util.Date;

public class ErrorMessage
{
	private int errorId;
	private String errorMessage;
	private Date timeStamp;

	public ErrorMessage(int errorId, String errorMessage, Date timeStamp)
	{
		super();
		this.errorId = errorId;
		this.errorMessage = errorMessage;
		this.timeStamp = timeStamp;
	}

	public int getErrorId()
	{
		return errorId;
	}

	public void setErrorId(int errorId)
	{
		this.errorId = errorId;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Date getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}

}
