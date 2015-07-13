package com.ibm.itacademy.animaland;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceSnapshot {
	private Long id;
	
	private Animal animal;
	
	private Date date;
	
	private List<Visitor> visitors = new ArrayList<Visitor>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

	public void add(Visitor visitor) {
		this.visitors.add(visitor);
	}

	@Override
	public String toString() {
		return "AttendanceSnapshot [animal=" + animal + ", date=" + date
				+ ", visitors=" + visitors + "]";
	}
	
	
}
