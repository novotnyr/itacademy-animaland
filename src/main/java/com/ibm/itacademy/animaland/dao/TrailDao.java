package com.ibm.itacademy.animaland.dao;

import java.util.List;

import com.ibm.itacademy.animaland.Trail;

public interface TrailDao {
	List<Trail> list();
	
	void insert(Trail trail);

	Trail update(Trail trail);

	Trail findById(Long id);
	
	void delete(Trail trail);
	
	List<Trail> search(String query);
}
