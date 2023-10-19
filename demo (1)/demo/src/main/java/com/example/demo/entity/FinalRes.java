package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private int userId;
	private String userName;
	private String quizTopic;
	private String categoryName;
	private String result;
	private String date_and_time;
	private int marks;
	private int resultId;
	private int max_marks;
	
}
