package com.ibm.itacademy.animaland;

import java.util.List;

public interface AnimalDao {
	List<Animal> list();
	
	Animal findById(Long id);
	
	void saveOrUpdate(Animal animal);
	
}
