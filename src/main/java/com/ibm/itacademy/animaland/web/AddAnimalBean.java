package com.ibm.itacademy.animaland.web;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;
import com.ibm.itacademy.animaland.AnimalDaoFactory;

@Named
public class AddAnimalBean {
	private static final Logger logger 
		= LoggerFactory.getLogger(AddAnimalBean.class);
	
	private AnimalDao animalDao 
		= AnimalDaoFactory.getInstance().getAnimalDao();
		
	private Animal animal = new Animal();
	
	public String submit() {
		logger.info("Saving animal {}", animal);
		animalDao.saveOrUpdate(animal);
		
		return "animals?faces-redirect=true";
	}
	
	public Animal getAnimal() {
		return animal;
	}
	
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
