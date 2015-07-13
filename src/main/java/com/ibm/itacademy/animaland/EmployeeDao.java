package com.ibm.itacademy.animaland;

import java.util.List;

public interface EmployeeDao {
	List<Employee> list();
	
	void saveOrUpdate(Employee employee);
	
	Employee findById(Long id);
}
