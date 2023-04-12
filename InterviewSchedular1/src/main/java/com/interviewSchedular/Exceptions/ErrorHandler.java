package com.interviewSchedular.Exceptions;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ErrorHandler {
	
	private String message;
	
	private LocalDateTime timeStamp;
	
	private String details;
	

}
