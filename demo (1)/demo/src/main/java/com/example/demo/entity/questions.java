package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class questions {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int qid;
	private String question;
	private String option1;
	private String option2;
    private String option3;
	private String option4;
	private String correct_option;
	
	
	@ManyToOne
    @JoinColumn(name = "qz_fk") 
	@JsonIgnoreProperties("que")
    private quiz qui; 
}

