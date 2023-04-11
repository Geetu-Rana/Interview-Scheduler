package com.interviewSchedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.interviewSchedular.Exceptions.InterviewException;
import com.interviewSchedular.Model.Interview;
import com.interviewSchedular.Service.InterviewImpl;
import com.interviewSchedular.Service.InterviewServImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ivSchedular")
public class InterviewController {
	
	private InterviewImpl ivServ;
	
	@Autowired
	public InterviewController(InterviewServImp ivServ) {
		// TODO Auto-generated constructor stub
		this.ivServ = ivServ;
	}
	
	@PostMapping("/interview")
	public ResponseEntity<Interview> createInterview( @Valid @RequestBody Interview iv ) throws InterviewException{
		Interview savedInt = ivServ.createInterview(iv);
		return new ResponseEntity<Interview>(savedInt, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Interview> uodateInterview(@Valid @RequestBody Interview id) throws InterviewException{
		Interview uodInt = ivServ.updateInterview(id);
		
		return new ResponseEntity<Interview>(uodInt, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<Interview> deleteInterview(@PathVariable("id") Long id ) throws InterviewException{
		Interview dI = ivServ.deleteInterview(id);
		
		return new ResponseEntity<Interview>(dI, HttpStatus.ACCEPTED);
	}
	
	
}
