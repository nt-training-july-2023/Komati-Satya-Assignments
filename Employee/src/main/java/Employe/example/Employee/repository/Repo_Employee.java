package Employe.example.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Employe.example.Employee.Entity.Employee;


@Repository
public interface Repo_Employee extends JpaRepository<Employee,Integer>{

}
