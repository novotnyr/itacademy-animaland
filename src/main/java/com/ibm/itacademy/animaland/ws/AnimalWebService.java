package com.ibm.itacademy.animaland.ws;

import java.util.Collections;
import java.util.List;

import javax.jws.WebService;

import com.ibm.itacademy.animaland.Animal;

@WebService
public class AnimalWebService {
	public List<Animal> getAnimals() {
		Animal animal = new Animal();
		animal.setSpecies("elephant");
		animal.setAge(24);
		
		return Collections.singletonList(animal);
	}
}	
