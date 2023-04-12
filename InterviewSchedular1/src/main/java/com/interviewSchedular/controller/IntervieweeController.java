package com.interviewSchedular.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewSchedular.Service.IntervieweeImpl;

@RestController
@RequestMapping("/interviewee")
public class IntervieweeController {
	
	private IntervieweeImpl ivServ;
	
	public IntervieweeController() {
		// TODO Auto-generated constructor stub
	}
}
