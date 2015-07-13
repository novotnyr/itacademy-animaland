package com.ibm.itacademy.animaland.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;
import com.ibm.itacademy.animaland.AnimalDaoFactory;

@ManagedBean
public class AnimalBean {
	@ManagedProperty("#{param.id}")
	private Long id;
	
	private AnimalDao animalDao = AnimalDaoFactory
			.getInstance()
			.getAnimalDao();
	
	public Animal getAnimal() {
		return animalDao.findById(id);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
