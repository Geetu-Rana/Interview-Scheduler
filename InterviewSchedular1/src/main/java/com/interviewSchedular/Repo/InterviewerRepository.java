package com.interviewSchedular.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewSchedular.Model.Interviewer;

public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

}
