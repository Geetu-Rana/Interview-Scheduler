package com.interviewSchedular.Service;

import java.util.List;
import java.util.Set;

import com.interviewSchedular.Exceptions.IntervieweeException;
import com.interviewSchedular.Model.Interview;
import com.interviewSchedular.Model.Interviewee;

public interface IntervieweeImpl {
	
	
	public Interviewee registerInterviewee(Interviewee interviewee) throws IntervieweeException;
	
	
	public Interviewee updateInterviewee(Interviewee interviewee) throws IntervieweeException;
	
	public Interviewee deleInterviewee(Long id) throws IntervieweeException;
	
	public List<Interviewee> getAllInterviewees() throws IntervieweeException;
	
	public List<Interview> getInterviewees(Long id) throws IntervieweeException;
	
	
}
