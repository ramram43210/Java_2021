package com.ram.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ram.model.ErrorMessage;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request)
	{
		System.out.println("------Inside handleAnyException--------");

		ErrorMessage errorMessage = new ErrorMessage(101, ex.toString(), new Date());

		return new ResponseEntity<>(errorMessage, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * @ExceptionHandler(value = NullPointerException.class) public
	 * ResponseEntity<Object>
	 * handleNullPointerException(NullPointerException ex, WebRequest
	 * request) { System.out.
	 * println("------Inside handleNullPointerException--------");
	 * 
	 * ErrorMessage errorMessage = new ErrorMessage(201,
	 * ex.toString(), new Date());
	 * 
	 * return new ResponseEntity<>(errorMessage, new HttpHeaders(),
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * @ExceptionHandler(value = EmployeeServiceException.class)
	 * public ResponseEntity<Object>
	 * handleEmployeeServiceException(EmployeeServiceException ex,
	 * WebRequest request) { System.out.
	 * println("------Inside EmployeeServiceException--------");
	 * 
	 * ErrorMessage errorMessage = new ErrorMessage(301,
	 * ex.toString(), new Date());
	 * 
	 * return new ResponseEntity<>(errorMessage,new HttpHeaders(),
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	@ExceptionHandler(value = {NullPointerException.class,EmployeeServiceException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request)
	{
		System.out.println("------Inside handleSpecificException--------");
		
		ErrorMessage errorMessage = new ErrorMessage(201, ex.toString(), new Date());
		
		return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}		
	

}
