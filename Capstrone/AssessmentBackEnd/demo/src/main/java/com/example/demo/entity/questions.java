package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
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
	@Column(nullable=false)
	private String question;
	@Column(nullable=false)
	private String option1;
	@Column(nullable=false)
	private String option2;
	@Column(nullable=false)
    private String option3;
	@Column(nullable=false)
	private String option4;
	@Column(nullable=false)
	private String correct_option;
	
	
	@ManyToOne
    @JoinColumn(name = "qz_fk") 
	@JsonIgnoreProperties("que")
    private quiz qui; 
}

