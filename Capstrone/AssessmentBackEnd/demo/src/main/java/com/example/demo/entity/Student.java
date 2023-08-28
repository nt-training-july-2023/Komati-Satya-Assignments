package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	@Column(nullable=false)
	@NotEmpty(message="userName is required")
	private String userName;
	@Column(nullable=false)
	@NotEmpty(message="email is required")
	@Pattern(regexp = "^[A-Z0-9a-z+_-]+@nucleusteq[.]com$", message = "Email is not valid")
	private String email;
	@Column(nullable=false)
	@NotEmpty(message="gender is required")
	private String gender;
	@Column(nullable=false)
	@NotEmpty(message="phoneNumber is required")
	@Pattern(regexp = "^[0-9]{10}$",message="Phone number must contain 10 digits")
	private String phoneNumber;
	@Column(nullable=false)
	@NotEmpty(message="role is required")
	private String role;
	@Column(nullable=false)
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=])(?=\\S+$).{8,}$",message="Minimum eight and m")
	@NotEmpty(message="password is required")
	private String password;
	@NotEmpty(message="dateOfBirth is required")
	@Column(nullable=false)
	private String dateOfBirth;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@OneToMany(targetEntity=StudentResult.class,cascade=CascadeType.ALL)
	@JoinColumn(name="stu_id",referencedColumnName="userId")
	@JsonIgnoreProperties("Student")
	private List<StudentResult> rs;

}