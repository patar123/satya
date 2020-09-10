package com.satyait.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "stdTab")
public class Student {
	
	@GeneratedValue
	@Id
	private Integer stdId;
	
	private String stdName;
	
	private Double stdFee;
	
	private String stdGender;
	
	private String course;
	
	private String address;
}
