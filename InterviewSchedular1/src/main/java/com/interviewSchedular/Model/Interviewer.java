package com.interviewSchedular.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Interviewer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long interviewerId;
	
	@NotNull(message = "Interviewer Name can not be null")
	@NotBlank(message = "Interviews name can not be Blank")
	private String interviewerName;
	
	private String interviewDetails;
	
	private String jobRole;
	
	private String jobDescription;
	
	private String location;
	
	private Integer ctc;
	
	private String addDetails;
	
	private String interviewerStatus;
	@JsonIgnore
	private Boolean deleteStatus;
	
}
