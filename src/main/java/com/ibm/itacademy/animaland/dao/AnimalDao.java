package com.ibm.itacademy.animaland.dao;

import java.util.List;

import com.ibm.itacademy.animaland.Animal;

public interface AnimalDao {
	List<Animal> list();
	
	Animal findById(Long id);
	
	void insert(Animal animal);

	Animal update(Animal animal);

}
