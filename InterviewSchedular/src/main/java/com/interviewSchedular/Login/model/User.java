package com.interviewSchedular.Login.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonEncoding;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "userName"),
		@UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@NotNull
	@UniqueElements
	private String userName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Email
	private String email;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy =  "user", fetch = FetchType.EAGER)
	private Set<Role> role = new HashSet<>();
	
	
}
