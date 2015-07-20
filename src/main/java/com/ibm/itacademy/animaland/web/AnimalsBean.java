package com.ibm.itacademy.animaland.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.dao.AnimalDao;

@Named
public class AnimalsBean {
	@Inject
	private AnimalDao animalDao;
	
	private List<Animal> animals;
	
	@PostConstruct
	public void refresh() {
		this.animals = animalDao.list();
	}
	
	public List<Animal> getAnimals() {
		return this.animals;
	}
	
}
