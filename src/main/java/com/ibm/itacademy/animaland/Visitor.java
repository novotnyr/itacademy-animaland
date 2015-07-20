package com.ibm.itacademy.animaland;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable
public class Visitor {
	
	private VisitorType type;

	public Visitor() {
		// empty constructor
	}
	
	public Visitor(VisitorType type) {
		this.type = type;
	}

	public VisitorType getType() {
		return type;
	}

	public void setType(VisitorType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Visitor))
			return false;
		Visitor other = (Visitor) obj;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
