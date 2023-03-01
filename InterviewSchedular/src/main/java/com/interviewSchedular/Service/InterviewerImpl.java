package com.interviewSchedular.Service;

import java.util.List;
import com.interviewSchedular.Exceptions.InterviewerException;
import com.interviewSchedular.Model.Interviewer;

public interface InterviewerImpl {
	
	public Interviewer saveInterview(Interviewer interview) throws InterviewerException;
	
	public Interviewer deleteInterviewer(Long interviewerId) throws InterviewerException;
	
	public Interviewer updateInterviewer(Interviewer interviewer) throws InterviewerException;
	
	public List<Interviewer> getAllInterviewer() throws InterviewerException;
	
	
}
