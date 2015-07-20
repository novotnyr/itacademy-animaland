package com.ibm.itacademy.animaland.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.Trail;

@Stateless
public class JpaTrailService implements TrailService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Animal> getAvailableAnimals(Trail trail) {
		String query = "select a from Trail as t, Animal as a where a not member t.animals";
		return entityManager.createQuery(query, Animal.class)
			.getResultList();
	}
	
	
}
