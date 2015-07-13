package com.ibm.itacademy.animaland.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;

@Named
@RequestScoped
public class EditAnimalBean {
	private static final Logger logger = LoggerFactory
			.getLogger(EditAnimalBean.class);

	@Inject
	private AnimalDao animalDao;
	
	@Inject
	@Param
	private Long id;
	
	private Animal animal;
	
	@PostConstruct
	public void init() {
		if(id == null) {
			this.animal = new Animal();
		} else {
			this.animal = animalDao.findById(id);
		}
	}
	
	public String submit() {
		logger.info("Saving animal {}", animal);
		animalDao.saveOrUpdate(animal);

		return "animals?faces-redirect=true";
	}

	public void setId(Long id) {
		logger.info("Setting animal ID: {}", id);
		this.id = id;
		logger.info("Animal ID set: {}", this.id);
	}
	
	public Long getId() {
		return id;
	}
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
