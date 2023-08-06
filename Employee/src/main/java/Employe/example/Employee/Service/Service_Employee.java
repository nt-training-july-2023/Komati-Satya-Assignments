package Employe.example.Employee.Service;

import java.util.List;
import java.util.Optional;

import Employe.example.Employee.Entity.Employee;

public interface Service_Employee {
 
	List<Employee> findAllMethods();
	Optional<Employee> findById(int id);
	void saveEmployee(List<Employee> emp);
	Employee updateEmployee(Employee emp,int id);
	void deleteEmploye(int id);
	
	
}
