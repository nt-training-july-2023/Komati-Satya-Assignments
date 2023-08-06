package Employe.example.Employee.ServiceImp;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import Employe.example.Employee.Entity.Employee;
import Employe.example.Employee.Service.Service_Employee;
import Employe.example.Employee.repository.Repo_Employee;

@Service
public class ServiceImp_Employe implements Service_Employee{
 @Autowired
  Repo_Employee repo;
  
 /*public ServiceImp_Employe(Repo_Employee repo) {
	  this.repo=repo;
  }*/

@Override
public List<Employee> findAllMethods() {
	
	return repo.findAll();
}
@Override
public Optional<Employee> findById(int id) {
	
	return repo.findById(id);
}
@Override
public void saveEmployee(List<Employee> employe) {
	for(Employee emp:employe) {
		 repo.save(emp);
	}
}

@Override
public Employee updateEmployee(Employee emp,int id) {
	return repo.save(emp);
}
@Override
public void deleteEmploye(int id) {
	repo.deleteById(id);
	
}

}
