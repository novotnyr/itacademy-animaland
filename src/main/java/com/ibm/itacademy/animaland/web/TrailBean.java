package com.ibm.itacademy.animaland.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;

import com.ibm.itacademy.animaland.Trail;
import com.ibm.itacademy.animaland.TrailDao;

@Named
@RequestScoped
public class TrailBean {
	@Inject
	private TrailDao trailDao;
	
	@Inject
	@Param
	private Long trailId;
	
	private Trail trail;
	
	@PostConstruct
	public void init() {
		this.trail = trailDao.findById(trailId);	
	}
	
	public Trail getTrail() {
		return trail;
	}
	
	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	
}
