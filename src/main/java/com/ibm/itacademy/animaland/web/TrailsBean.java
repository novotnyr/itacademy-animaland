package com.ibm.itacademy.animaland.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.Trail;
import com.ibm.itacademy.animaland.TrailDao;

@Named
@ViewScoped
public class TrailsBean implements Serializable {
	@Inject
	private TrailDao trailDao;
	
	private List<Trail> trails = new LinkedList<Trail>();
	
	@PostConstruct
	public void init() {
		trails = trailDao.list();
	}
	
	public void delete(Trail trail) {
		trailDao.delete(trail);
		init();
	}
	
	public void submit() {
		Trail trail = new Trail();
		trail.setDescription("African animals");
		
		trailDao.saveOrUpdate(trail);
		init();
	}
	
	
	
	public List<Trail> getTrails() {
		return trails;
	}
}
