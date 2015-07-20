package com.ibm.itacademy.animaland.web;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AttendanceSnapshot;
import com.ibm.itacademy.animaland.Visitor;
import com.ibm.itacademy.animaland.VisitorType;
import com.ibm.itacademy.animaland.dao.AnimalDao;
import com.ibm.itacademy.animaland.service.AttendanceTrackingService;

@Named
@ViewScoped
public class SnapshotBean implements Serializable {
	@Inject
	private transient AttendanceTrackingService attendanceTrackingService;
	
	@Inject
	private transient AnimalDao animalDao;
	
	private long animalId;
	
	@Min(value = 0, message = "Please enter at least one adult")
	@Max(value = 25, message = "Maximum 25 adults")
	private int adults;
	
	@Min(0)
	@Max(25)
	private int children;

	private List<Animal> animals = new LinkedList<Animal>();
	
	private List<AttendanceSnapshot> snapshots 
		= new LinkedList<AttendanceSnapshot>();
	
	@PostConstruct
	public void init() {
		this.animals = animalDao.list();
		refreshSnapshots();
	}
	
	public void delete(AttendanceSnapshot snapshot) {
		attendanceTrackingService.delete(snapshot);
		refreshSnapshots();
	}

	private void refreshSnapshots() {
		this.snapshots = attendanceTrackingService.list();
	}

	public List<AttendanceSnapshot> getSnapshots() {
		return this.snapshots;
	}
	
	public void insertSnapshot() {
		AttendanceSnapshot snapshot = new AttendanceSnapshot();
		snapshot.setDate(new Date());
		snapshot.setAnimal(animalDao.findById(animalId));
		for (int i = 0; i < adults; i++) {
			snapshot.add(new Visitor(VisitorType.ADULT));
		}
		for (int i = 0; i < children; i++) {
			snapshot.add(new Visitor(VisitorType.CHILD));
		}
		attendanceTrackingService.insert(snapshot);
		refreshSnapshots();
	}
	
	public List<Animal> getAnimals() {
		return this.animals;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public long getAnimalId() {
		return animalId;
	}
	
	public void setAnimalId(long animalId) {
		this.animalId = animalId;
	}
	
	
	
	
	
	
	
	
	
}
