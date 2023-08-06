package Employe.example.Employee.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
  @Id
 // @GeneratedValue(strategy=GenerationType.AUTO)
  private int employeId;
  private String employeName;
  private String email;
  private int salary;
  private String address;
public int getEmployeId() {
	return employeId;
}
public void setEmployeId(int employeId) {
	this.employeId = employeId;
}
public String getEmployeName() {
	return employeName;
}
public void setEmployeName(String employeName) {
	this.employeName = employeName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee(int employeId, String employeName, String email, int salary, String address) {
	super();
	this.employeId = employeId;
	this.employeName = employeName;
	this.email = email;
	this.salary = salary;
	this.address = address;
}
@Override
public String toString() {
	return "Employee [employeId=" + employeId + ", employeName=" + employeName + ", email=" + email + ", salary="
			+ salary + ", address=" + address + "]";
}
  
  
  
  
}
