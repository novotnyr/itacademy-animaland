package com.ibm.itacademy.animaland;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class JpaEmployeeDao implements EmployeeDao {
	private static final Logger logger 
		= LoggerFactory.getLogger(JpaEmployeeDao.class);

	@PersistenceContext
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

		return null;
	}

}
