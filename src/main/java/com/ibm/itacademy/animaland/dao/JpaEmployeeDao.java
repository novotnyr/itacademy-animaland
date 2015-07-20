package com.ibm.itacademy.animaland.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.itacademy.animaland.Employee;

@Stateful
public class JpaEmployeeDao implements EmployeeDao {
	private static final Logger logger 
		= LoggerFactory.getLogger(JpaEmployeeDao.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> list() {
		logger.info("list()");

		String query = "SELECT employee FROM Employee employee";
		return entityManager
			.createQuery(query)
			.getResultList();
	}

	@Override
	public void saveOrUpdate(Employee employee) {
		logger.info("Save or update");
		entityManager.persist(employee);
	}

	@Override
	public Employee findById(Long id) {
		logger.info("findById()");
		return entityManager.find(Employee.class, id);
	}

}
