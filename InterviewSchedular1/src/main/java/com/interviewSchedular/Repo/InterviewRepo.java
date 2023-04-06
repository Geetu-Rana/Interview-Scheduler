package com.interviewSchedular.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewSchedular.Model.Interview;

public interface InterviewRepo extends JpaRepository<Interview, Long> {

}
