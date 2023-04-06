package com.interviewSchedular.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewSchedular.Exceptions.InterviewException;
import com.interviewSchedular.Model.Interview;
import com.interviewSchedular.Repo.InterviewRepo;
@Service
public class InterviewServImp implements InterviewImpl {
	@Autowired
	private InterviewRepo ivRepo;
	
	@Override
	public Interview createInterview(Interview interview) throws InterviewException {
		Optional<Interview> opt = ivRepo.findById(interview.getInterviewId());
		
		if(opt.isPresent()) {
			throw new InterviewException("Interview Already present!");
		}else {
			return ivRepo.save(interview);
		}
		
	}

	@Override
	public Interview updateInterview(Interview interview) throws InterviewException {
		Optional<Interview> opt = ivRepo.findById(interview.getInterviewId());
		
		if(opt.isEmpty()) {
			throw new InterviewException("No interview exist !");
		}else {
			return ivRepo.save(interview);
		}
	}

	@Override
	public Interview deleteInterview(Long InterviewId) throws InterviewException {
		// TODO Auto-generated method stub
		
		return null;
	}

}
