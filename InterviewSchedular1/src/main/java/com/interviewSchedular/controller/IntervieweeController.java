package com.interviewSchedular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewSchedular.Exceptions.IntervieweeException;
import com.interviewSchedular.Model.Interview;
import com.interviewSchedular.Model.Interviewee;
import com.interviewSchedular.Service.IntervieweeImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/interviewee")
public class IntervieweeController {
	
	private IntervieweeImpl ivServ;
	
	@Autowired
	public IntervieweeController(IntervieweeImpl ivImpl) {
		// TODO Auto-generated constructor stub
		this.ivServ = ivImpl;
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<Interviewee> saveinterviewee(@Valid @RequestBody Interviewee  interviewee) throws IntervieweeException{
		Interviewee iv = ivServ.registerInterviewee(interviewee);
		
		return new ResponseEntity<Interviewee>(iv, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Interviewee> updateInerviewee(@Valid @RequestBody Interviewee interviewee) throws IntervieweeException{
		Interviewee iv = ivServ.updateInterviewee(interviewee);
		
		return new ResponseEntity<Interviewee>(iv, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Interviewee> deleEntity(@PathVariable("id") Long id) throws IntervieweeException{
		Interviewee iv = ivServ.deleInterviewee(id);
		
		return new ResponseEntity<Interviewee>(iv, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Interviewee>> getALlInterviwee() throws IntervieweeException{
		List<Interviewee> list = ivServ.getAllInterviewees();
		
		return new ResponseEntity<List<Interviewee>>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getInterviewees/{id}")
	public ResponseEntity<List<Interview>> getAllInterviews(@PathVariable("id") Long id) throws IntervieweeException{
		List<Interview> list = ivServ.getInterviewees(id);
		return new ResponseEntity<List<Interview>>(list, HttpStatus.ACCEPTED);
	}
}
