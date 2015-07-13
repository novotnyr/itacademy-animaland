package com.ibm.itacademy.animaland.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;

@Named
public class AnimalsBean {
	@Inject
	private AnimalDao animalDao;
	
	public List<Animal> getAnimals() {
		return animalDao.list();
	}
	
	public void delete(Animal animal) {
		System.err.println("Deleting an animal..." + animal);
	}
}
