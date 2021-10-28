package com.ram.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ram.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler
{

	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex,
			WebRequest request)
	{
		System.out.println("------Inside handleUserServiceException--------");
		ex.printStackTrace();

		ErrorMessage errorMessage = new ErrorMessage(201, ex.toString(), new Date());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request)
	{
		System.out.println("------Inside handleAnyException--------");

		ex.printStackTrace();

		ErrorMessage errorMessage = new ErrorMessage(301, ex.toString(), new Date());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
