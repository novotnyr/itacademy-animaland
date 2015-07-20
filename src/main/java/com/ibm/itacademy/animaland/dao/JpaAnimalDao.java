package com.ibm.itacademy.animaland.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.ibm.itacademy.animaland.Animal;

@Stateful
public class JpaAnimalDao implements AnimalDao {
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Override
	public List<Animal> list() {
		String query = "SELECT a FROM Animal a";
		return entityManager
				.createQuery(query, Animal.class)
				.getResultList();
	}

	@Override
	public Animal findById(Long id) {
		return entityManager.find(Animal.class, id);
	}

	@Override
	public void insert(Animal animal) {
		entityManager.persist(animal);
	}
	
	@Override
	public Animal update(Animal animal) {
		return entityManager.merge(animal);
	}

}
