package com.demo.dao.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String surname;
	@Column(nullable=false,unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	private LocalDate birthDate;
	private Integer age;
	private Gender gender;
}
