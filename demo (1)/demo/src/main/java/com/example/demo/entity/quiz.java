package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class quiz{

	@Id
	private int quiz_Id;
	private String topic_Name;
	private String topic_Description;
	private int max_Marks;
	private int pass_Marks;	
   
	@ManyToOne
    @JoinColumn(name = "c_fk") 
	@JsonIgnoreProperties("qu")
    private category cate; 
	
	@OneToMany(targetEntity=StudentResult.class,cascade=CascadeType.ALL)
	@JoinColumn(name="qz_id",referencedColumnName="quiz_Id")
	@JsonIgnoreProperties("quiz")
	@JsonIgnore
	private List<StudentResult> se;

	@OneToMany(targetEntity=questions.class,cascade=CascadeType.ALL)
	@JoinColumn(name="qz_fk",referencedColumnName="quiz_id")
	@JsonIgnoreProperties("quiz")
	private List<questions> que;
    
//	@OneToMany(targetEntity=StudentResult.class,cascade=CascadeType.ALL)
//	@JoinColumn(name="qz_id",referencedColumnName="quiz_Id")
//	@JsonIgnoreProperties("quiz")
//	private List<StudentResult> se;

}
