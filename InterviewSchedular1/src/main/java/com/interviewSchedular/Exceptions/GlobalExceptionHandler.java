package com.interviewSchedular.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IntervieweeException.class)
	public ResponseEntity<ErrorHandler> intervieweeExceptionHandler(IntervieweeException ic, WebRequest req){
		
		ErrorHandler eh = new ErrorHandler();
		eh.setTimeStamp(LocalDateTime.now());
		eh.setMessage(ic.getMessage());
		eh.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorHandler>(eh, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InterviewException.class)
	public ResponseEntity<ErrorHandler> interviewExceptionHandler(InterviewException ic, WebRequest req){
		
		ErrorHandler eh = new ErrorHandler();
		eh.setTimeStamp(LocalDateTime.now());
		eh.setMessage(ic.getMessage());
		eh.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorHandler>(eh, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InterviewerException.class)
	public ResponseEntity<ErrorHandler> interviewerExceptionHandler(InterviewerException ic, WebRequest req){
		
		ErrorHandler eh = new ErrorHandler();
		eh.setTimeStamp(LocalDateTime.now());
		eh.setMessage(ic.getMessage());
		eh.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorHandler>(eh, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorHandler> validationExceptionHandler(MethodArgumentNotValidException ic, WebRequest req){
		
		ErrorHandler eh = new ErrorHandler();
		eh.setTimeStamp(LocalDateTime.now());
		eh.setMessage(ic.getMessage());
		eh.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorHandler>(eh, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorHandler> aLlExceptionHandler(Exception ic, WebRequest req){
		
		ErrorHandler eh = new ErrorHandler();
		eh.setTimeStamp(LocalDateTime.now());
		eh.setMessage(ic.getMessage());
		eh.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorHandler>(eh, HttpStatus.BAD_REQUEST);
	}
	
}
