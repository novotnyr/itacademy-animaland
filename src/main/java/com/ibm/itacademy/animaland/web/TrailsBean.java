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
import com.ibm.itacademy.animaland.dao.TrailDao;

@Named
@ViewScoped
public class TrailsBean implements Serializable {
	@Inject
	private TrailDao trailDao;
	
	private List<Trail> trails = new LinkedList<Trail>();
	
	private Trail trail;
	
	private String query;
	
	@PostConstruct
	public void refresh() {
		trails = trailDao.list();
		trail = new Trail();
	}
	
	public void delete(Trail trail) {
		trailDao.delete(trail);
		refresh();
	}
	
	public void submit() {
		trailDao.insert(trail);
		refresh();
	}
	
	public void search() {
		System.out.println("Searching " + query);
		
		this.trails = trailDao.search(query);
	}
	
	public List<Trail> getTrails() {
		return trails;
	}
	
	public Trail getTrail() {
		return trail;
	}
	
	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
}
