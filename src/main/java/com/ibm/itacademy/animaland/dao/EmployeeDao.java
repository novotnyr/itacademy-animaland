package com.ibm.itacademy.animaland.dao;

import java.util.List;

import com.ibm.itacademy.animaland.Employee;

public interface EmployeeDao {
	List<Employee> list();
	
	void saveOrUpdate(Employee employee);
	
	Employee findById(Long id);
}
