package com.ibm.itacademy.animaland.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;

import com.ibm.itacademy.animaland.Animal;

@Named
@RequestScoped
public class AnimalBean {
	@Inject
	@Param(name = "id", converter="#{animalConverter}")
	private Animal animal;
	
	public Animal getAnimal() {
		return animal;
	}
	
}
