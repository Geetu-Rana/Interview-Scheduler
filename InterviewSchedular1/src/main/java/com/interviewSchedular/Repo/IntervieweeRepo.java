package com.interviewSchedular.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewSchedular.Model.Interviewee;

public interface IntervieweeRepo extends JpaRepository<Interviewee, Long> {

}
