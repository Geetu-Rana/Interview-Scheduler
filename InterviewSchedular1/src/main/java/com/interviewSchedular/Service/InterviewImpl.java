package com.interviewSchedular.Service;

import com.interviewSchedular.Exceptions.InterviewException;
import com.interviewSchedular.Model.Interview;

public interface InterviewImpl {
	
	public Interview createInterview(Interview interview) throws InterviewException;
	
	public Interview updateInterview(Interview interview) throws InterviewException;
	
	public Interview deleteInterview(Long InterviewId) throws InterviewException;
	
}