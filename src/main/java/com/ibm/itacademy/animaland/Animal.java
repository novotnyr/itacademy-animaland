package com.ibm.itacademy.animaland;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Animal {
	@Id
	@GeneratedValue
	private Long id;
	
	private String species;
	
	private int age;
	
	private boolean male;
	
	private String diet;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Animal)) return false;

		Animal animal = (Animal) o;

		if (getAge() != animal.getAge()) return false;
		if (isMale() != animal.isMale()) return false;
		if (getSpecies() != null ? !getSpecies().equals(animal.getSpecies()) : animal.getSpecies() != null)
			return false;
		return !(getDiet() != null ? !getDiet().equals(animal.getDiet()) : animal.getDiet() != null);

	}

	@Override
	public int hashCode() {
		int result = getSpecies() != null ? getSpecies().hashCode() : 0;
		result = 31 * result + getAge();
		result = 31 * result + (isMale() ? 1 : 0);
		result = 31 * result + (getDiet() != null ? getDiet().hashCode() : 0);
		return result;
	}
}
