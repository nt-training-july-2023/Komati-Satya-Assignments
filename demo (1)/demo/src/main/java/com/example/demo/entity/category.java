package com.example.demo.entity;


	import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Entity
	public class category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int category_Id;
	private String category_Name;
	private String category_Description;
	
	@OneToMany(targetEntity=quiz.class,cascade=CascadeType.ALL)
	@JoinColumn(name="c_fk",referencedColumnName="category_Id")
	@JsonIgnoreProperties("category")
	private List<quiz> qu;
	
//	@ManyToMany(targetEntity=StudentResult.class,cascade=CascadeType.ALL)
//	@JoinColumn(name="c_id",referencedColumnName="category_Id")
//	@JsonIgnoreProperties("category")
//	private StudentResult re;
	
}
