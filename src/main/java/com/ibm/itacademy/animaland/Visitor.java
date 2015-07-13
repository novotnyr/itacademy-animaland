package com.ibm.itacademy.animaland;

public class Visitor {
	private Long id;
	
	private VisitorType type;

	public VisitorType getType() {
		return type;
	}

	public void setType(VisitorType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
