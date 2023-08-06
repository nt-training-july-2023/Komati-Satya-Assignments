package Employe.example.Employee.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Employe.example.Employee.Entity.Employee;
import Employe.example.Employee.Service.Service_Employee;


@RestController
public class Controller_Employee {

	private final Service_Employee s;
	public Controller_Employee(Service_Employee s)
	{
		this.s=s;
	}
	@GetMapping("/Employee")
	public List<Employee> findAllMethods() {
		
		return s.findAllMethods();
	}
	@GetMapping("/Employee/{id}")
	public Optional<Employee> findById(@PathVariable int id) {
		
		return s.findById(id);
	}
	@PostMapping("/Employee")
	public void saveEmployee(@RequestBody List<Employee> emp) {
			 s.saveEmployee(emp);
	}

	@PutMapping("/Employee/{id}")
	public Employee updateEmployee(@RequestBody Employee emp,@PathVariable int id) {
		return s.updateEmployee(emp,id);
	}
	@DeleteMapping("/Employee/{id}")
	public void deleteEmploye(@PathVariable int id) {
		s.deleteEmploye(id);
		
	}

	

}
