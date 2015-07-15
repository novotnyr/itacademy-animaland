package com.ibm.itacademy.animaland;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TrailService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Animal> getAvailableAnimals(Trail trail) {
		String sql = "select animal.*"
					 + " from animal "
					 + " left outer join trail_animal "
					 + " on trail_animal.animals_id = animal.id"
					 + " where trail_animal.trail_id IS NULL";
	
		return entityManager.createNativeQuery(sql, Animal.class)
			.getResultList();
	}
	
	
}
