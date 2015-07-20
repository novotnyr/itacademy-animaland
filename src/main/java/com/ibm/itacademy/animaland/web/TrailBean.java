package com.ibm.itacademy.animaland.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.Employee;
import com.ibm.itacademy.animaland.Trail;
import com.ibm.itacademy.animaland.dao.AnimalDao;
import com.ibm.itacademy.animaland.dao.EmployeeDao;
import com.ibm.itacademy.animaland.dao.TrailDao;
import com.ibm.itacademy.animaland.service.TrailService;

@Named
@ViewScoped
public class TrailBean implements Serializable {
	@Inject
	private TrailDao trailDao;
	
	@Inject
	private transient AnimalDao animalDao;
	
	@Inject
	private TrailService trailService;
	
	@Inject
	private EmployeeDao employeeDao;
	
	@Inject
	@Param
	private Long trailId;
	
	private Trail trail;
	
	private Long selectedAnimalId;

	private List<Animal> availableAnimals;
	
	private Long selectedEmployeeId;
	
	@PostConstruct
	public void init() {
		this.trail = trailDao.findById(trailId);	
		
		availableAnimals = trailService.getAvailableAnimals(trail);
	}
	
	public String assignAnimal() {
		Animal animal = animalDao.findById(selectedAnimalId);
		trail.addAnimal(animal);
		trailDao.update(trail);
		
		return "trail?faces-redirect=true&trailId=" + trailId;
	}
	
	public void assignEmployee() {
		Employee employee = employeeDao.findById(selectedEmployeeId);
		trail.setEmployee(employee);
		trailDao.update(trail);
	}
	
	
	public Trail getTrail() {
		return trail;
	}
	
	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	public Long getSelectedAnimalId() {
		return selectedAnimalId;
	}
	
	public void setSelectedAnimalId(Long selectedAnimalId) {
		this.selectedAnimalId = selectedAnimalId;
	}
	
	public List<Animal> getAvailableAnimals() {
		return availableAnimals;
	}

	public Long getSelectedEmployeeId() {
		return selectedEmployeeId;
	}

	public void setSelectedEmployeeId(Long selectedEmployeeId) {
		this.selectedEmployeeId = selectedEmployeeId;
	}
	
	
	
}
