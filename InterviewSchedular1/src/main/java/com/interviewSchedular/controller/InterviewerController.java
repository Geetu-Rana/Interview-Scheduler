package com.interviewSchedular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewSchedular.Exceptions.InterviewerException;
import com.interviewSchedular.Model.Interviewer;
import com.interviewSchedular.Service.InterviewerImpl;
import com.interviewSchedular.Service.InterviewerServImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/interviewer")
public class InterviewerController {
	
	private InterviewerImpl ivServ;
	
	@Autowired
	public InterviewerController(InterviewerServImpl iv) {
		// TODO Auto-generated constructor stub
		this.ivServ = iv;
	}
	
	@PostMapping("/interviewer")
	public ResponseEntity<Interviewer> registerInterviewer(@Valid @RequestBody Interviewer iv) throws InterviewerException{
		Interviewer inv = ivServ.saveInterview(iv);
		return new ResponseEntity<Interviewer>(inv, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/updateInter")
	public ResponseEntity<Interviewer> updateInterviewer(@Valid @RequestBody Interviewer iv) throws InterviewerException{
		
		Interviewer inv = ivServ.updateInterviewer(iv);
		
		return new ResponseEntity<Interviewer>(inv, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Interviewer> deleteInterviewer(@PathVariable("id") Long id) throws InterviewerException{
		Interviewer iv  = ivServ.deleteInterviewer(id);
		
		return new ResponseEntity<Interviewer>(iv, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Interviewer>> getAllInterviwer() throws InterviewerException{
		List<Interviewer> list = ivServ.getAllInterviewer();
		
		return new ResponseEntity<List<Interviewer>>(list,HttpStatus.ACCEPTED);
	}
	
}
