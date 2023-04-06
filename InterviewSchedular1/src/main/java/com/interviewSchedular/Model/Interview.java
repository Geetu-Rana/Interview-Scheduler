package com.interviewSchedular.Model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Interview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long interviewId;
	
	@NotBlank
	@NotNull
	private String interviewType;
	
	@FutureOrPresent(message = "Starting Date can not be in Past.")
	private LocalDateTime StartingDateTime;
	
	@FutureOrPresent(message = "Ending Date shuold be in future.")
	private LocalDateTime EndingDateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Interviewer interviewer;
	
	@OneToMany
	@JoinColumn(name = "candidate_Id")
	private Set<Interviewee> interviewees;
	
}
