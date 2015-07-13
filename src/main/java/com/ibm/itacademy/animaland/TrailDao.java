package com.ibm.itacademy.animaland;

import java.util.List;

public interface TrailDao {
	List<Trail> list();
	
	void saveOrUpdate(Trail trail);
	
	Trail findById(Long id);
	
	void delete(Trail trail);
}
