package com.interviewSchedular.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewSchedular.Exceptions.InterviewException;
import com.interviewSchedular.Exceptions.InterviewerException;
import com.interviewSchedular.Model.Interview;
import com.interviewSchedular.Model.Interviewer;
import com.interviewSchedular.Repo.InterviewRepo;
import com.interviewSchedular.Repo.InterviewerRepository;
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
		
		if(opt.isEmpty() || opt.get().getDeleteState()) {
			throw new InterviewException("No interview exist or Interview Deleted!");
		}else {
			return ivRepo.save(interview);
		}
	}

	@Override
	public Interview deleteInterview(Long InterviewId) throws InterviewException {
		// TODO Auto-generated method stub
		Optional<Interview> opt = ivRepo.findById(InterviewId);
		if(opt.isEmpty()) {
			throw new InterviewException("No iterview Exist!");
		}
		opt.get().setDeleteState(true);
		return opt.get();
	}
	
	public List<Interview> getAllInterview() throws InterviewException{
			
		List<Interview> list = ivRepo.findAll();
		
		if(list.isEmpty()) {
			throw new InterviewException("No interviews exist");
		}
		return list;
	}
	
	public List<Interview> companiesInterviews(Long intervierId) throws InterviewerException{
		List<Interview> list = ivRepo.findAll();
		
		
		
		List<Interview> activeInterview = new ArrayList<>();
		for(Interview i : list) {
			if(i.getInterviewer().getInterviewerId() == intervierId && i.getDeleteState() == false) {
				activeInterview.add(i);
			}
		}
		if(activeInterview.isEmpty()) {
			throw new InterviewerException("No active interview exist ");
		}
		return activeInterview;
		
	}

}
