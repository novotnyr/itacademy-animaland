package com.ibm.itacademy.animaland.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.Trail;
import com.ibm.itacademy.animaland.dao.TrailDao;

@Named
@RequestScoped
public class SearchBean {
	@Inject
	private TrailDao trailDao;
	
	private String query;
	
	private List<Trail> results;
	
	public void search() {
		results = trailDao.search(query);
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public List<Trail> getResults() {
		return results;
	}
	
	public void setResults(List<Trail> results) {
		this.results = results;
	}

}
