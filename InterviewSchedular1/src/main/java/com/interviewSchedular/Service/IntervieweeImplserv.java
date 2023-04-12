package com.interviewSchedular.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewSchedular.Exceptions.IntervieweeException;
import com.interviewSchedular.Model.Interview;
import com.interviewSchedular.Model.Interviewee;
import com.interviewSchedular.Repo.InterviewRepo;
import com.interviewSchedular.Repo.IntervieweeRepo;

@Service
public class IntervieweeImplserv implements IntervieweeImpl{
	
	private IntervieweeRepo ivRepo;
	
	private InterviewRepo ivrRepo;
	@Autowired
	public IntervieweeImplserv(IntervieweeRepo iv) {
		// TODO Auto-generated constructor stub
		this.ivRepo = iv;
	}
	public IntervieweeImplserv(InterviewRepo iv) {
		// TODO Auto-generated constructor stub
		this.ivrRepo = iv;
	}
	
	
	@Override
	public Interviewee registerInterviewee(Interviewee interviewee) throws IntervieweeException {
		Optional<Interviewee> optional =  ivRepo.findById(interviewee.getIntervieweeId());
			if(optional.isPresent()) {
				throw new IntervieweeException("inteviewee allready Present");
			}
		return ivRepo.save(interviewee);
	}

	@Override
	public Interviewee updateInterviewee(Interviewee interviewee) throws IntervieweeException {
		// TODO Auto-generated method stub
		Optional<Interviewee> opt = ivRepo.findById(interviewee.getIntervieweeId());
			
		if(opt.isEmpty()) {
			throw new IntervieweeException("Interview not exist ");
		}
		
		return ivRepo.save(interviewee);
	}

	@Override
	public Interviewee deleInterviewee(Interviewee interviewee) throws IntervieweeException {
		Optional<Interviewee> opt = ivRepo.findById(interviewee.getIntervieweeId());
		if(opt.isEmpty()) {
			throw new IntervieweeException("Interviewee not exist");
		}
		return opt.get();
	}

	@Override
	public List<Interviewee> getAllInterviewees() throws IntervieweeException {
		List<Interviewee> list = ivRepo.findAll();
		if(list.isEmpty()) {
			throw new IntervieweeException("No Interviewee Exist !");
		}
		return list;
	}

	@Override
	public List<Interview> getInterviewees(Long id) throws IntervieweeException {
		// TODO Auto-generated method stub
		List<Interview> list = ivrRepo.findAll();
		
		
		List<Interview> ansList = new ArrayList<>();
		for(Interview i : list) {
			if(i.getInterviewer().getInterviewerId()==id) {
				ansList.add(i);
			}
		}
		if(ansList.isEmpty()) {
			throw new IntervieweeException("No Interview exist ");
		}
		return ansList;
	}
		
}
