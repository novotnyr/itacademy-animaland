package com.ibm.itacademy.animaland;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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
	public void saveOrUpdate(Trail trail) {
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
	
	
	
	
	
	
	
	
	
	
	
	
	

}
