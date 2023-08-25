package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class FinalRes {
     
	@Id
	private int finalId;
	@Column(nullable=false)
	private int userId;
	@Column(nullable=false)
	private String userName;
	@Column(nullable=false)
	private String quizTopic;
	@Column(nullable=false)
	private String categoryName;
	@Column(nullable=false)
	private String result;
	@Column(nullable=false)
	private String date_and_time;
	@Column(nullable=false)
	private int marks;
	@Column(nullable=false)
	private int resultId;
	@Column(nullable=false)
	private int max_marks;
	
}
