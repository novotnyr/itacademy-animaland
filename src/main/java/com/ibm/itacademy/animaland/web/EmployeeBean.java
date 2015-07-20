package com.ibm.itacademy.animaland.web;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.animaland.Employee;
import com.ibm.itacademy.animaland.dao.EmployeeDao;

@Named
@ViewScoped
public class EmployeeBean implements Serializable {
	@Inject
	private EmployeeDao employeeDao;
	
	private Employee employee;
	
	private List<Employee> employees = Collections.emptyList();
	
	@PostConstruct
	public void refresh() {
		this.employee = new Employee();
		this.employees = employeeDao.list();
	}
	
	public List<Employee> getEmployees() {
		return this.employees;
	}
	
	public void submit() {
		employeeDao.saveOrUpdate(employee);
		refresh();
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
