package com.sathya.restapiproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.restapiproject.data.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Optional<Employee>findByEmail(String email);
	List<Employee>findByDeptAndGender(String dept,String gender);
	List<Employee>findByDeptOrGender(String dept,String gender);
	List<Employee>findBySalaryBetween(double minSalary,double maxSalary);
	List<Employee>findBySalaryGreaterThan(double salary);
	List<Employee>findBySalaryLessThan(double salary);

}
