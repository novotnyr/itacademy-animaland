package com.ibm.itacademy.animaland.web;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.Trail;
import com.ibm.itacademy.animaland.TrailDao;

@Named
@RequestScoped
public class TrailsBean {
	@Inject
	private TrailDao trailDao;
	
	private List<Trail> trails = new LinkedList<Trail>();
	
	@PostConstruct
	public void init() {
		trails = trailDao.list();
		for (Trail trail : trails) {
			System.out.println(trail.getAnimals());
			
			System.out.println(trail);
			
			for (Animal animal : trail.getAnimals()) {
				System.out.println(animal.getClass());
			}
		}
	}
	
	public void submit() {
		Trail trail = new Trail();
		trail.setDescription("African animals");
		
		trailDao.saveOrUpdate(trail);
	}
	
	public List<Trail> getTrails() {
		return trails;
	}
}
