package com.ibm.itacademy.animaland;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AttendanceSnapshot {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Animal animal;
	
	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ElementCollection
	private Set<Visitor> visitors = new HashSet<Visitor>();
	
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

	public void add(Visitor visitor) {
		this.visitors.add(visitor);
	}

	@Override
	public String toString() {
		return "AttendanceSnapshot [animal=" + animal + ", date=" + date
				+ ", visitors=" + visitors + "]";
	}

	public Set<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(Set<Visitor> visitors) {
		this.visitors = visitors;
	}
	
	
}
