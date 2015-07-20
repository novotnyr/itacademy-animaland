package com.ibm.itacademy.animaland.service;

import java.util.List;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.Trail;

public interface TrailService {

	List<Animal> getAvailableAnimals(Trail trail);

}