package com.ibm.itacademy.animaland.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.ibm.itacademy.animaland.Trail;

@Stateful
public class JpaTrailDao implements TrailDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Override
	public List<Trail> list() {
		String query = "SELECT t FROM Trail t";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void insert(Trail trail) {
		entityManager.persist(trail);
	}

	@Override
	public Trail findById(Long id) {
		return entityManager.find(Trail.class, id);
	}

	@Override
	public void delete(Trail trail) {
		entityManager.remove(trail);
	}

	@Override
	public List<Trail> search(String query) {

		String jpaQuery 
			= "SELECT t FROM Trail t WHERE t.description LIKE :query";
		return entityManager
				.createQuery(jpaQuery, Trail.class)
				.setParameter("query", query)
				.getResultList();
	}

	@Override
	public Trail update(Trail trail) {
		return entityManager.merge(trail);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
