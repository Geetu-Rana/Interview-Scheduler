package com.interviewSchedular.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewSchedular.Exceptions.InterviewerException;
import com.interviewSchedular.Model.Interviewer;
import com.interviewSchedular.Repo.InterviewerRepository;
@Service
public class InterviewerServImpl implements InterviewerImpl{
	
	@Autowired
	private InterviewerRepository ivRepo;
	
	
	@Override
	public Interviewer saveInterview(Interviewer interviewer) throws InterviewerException {
		// TODO Auto-generated method stub
		 
		return ivRepo.save(interviewer);
	}

	@Override
	public Interviewer deleteInterviewer(Long interviewerId) throws InterviewerException {
		// TODO Auto-generated method stub
		Optional<Interviewer> optional = ivRepo.findById(interviewerId);
		
		if(optional.isEmpty()) {
			throw new InterviewerException("No intervier Present with interviewerId " +interviewerId);
		}else {
			
			Interviewer iv = optional.get();
			iv.setInterviewerStatus("Closed");
			return ivRepo.save(iv);
		}
		
		
	}

	@Override
	public Interviewer updateInterviewer(Interviewer interviewer) throws InterviewerException {
		// TODO Auto-generated method stub
		Interviewer iv = ivRepo.findById(interviewer.getInterviewerId()).get();
		if(iv == null) {
			throw new InterviewerException("No interviewr exist with id "+ interviewer.getInterviewerId());
		}else {
			return ivRepo.save(iv);
		}
		
	}

	@Override
	public List<Interviewer> getAllInterviewer() throws InterviewerException {
		// TODO Auto-generated method stub 
		List<Interviewer> list = ivRepo.findAll();
		if(list.isEmpty()) {
			throw new InterviewerException("No interviewer exist");
		}else {
			return list;
		}
	}

}