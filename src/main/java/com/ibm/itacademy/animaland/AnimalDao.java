package com.ibm.itacademy.animaland;

import java.util.List;

/**
 * 
 * Represents a CRUD DAO for Animals.
 *
 */
public interface AnimalDao {
	List<Animal> list();
	
	/**
	 * Returns an animal by its primary key.
	 * @param id a non-null identifier
	 * @return an animal instance or null when no such animal is found
	 * @throws DatabaseException on database or SQL error
	 */
	Animal findById(Long id);
	
	void saveOrUpdate(Animal animal);

	void delete(Animal animal);
	
}
