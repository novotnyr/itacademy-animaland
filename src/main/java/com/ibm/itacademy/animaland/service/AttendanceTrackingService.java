package com.ibm.itacademy.animaland.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AttendanceSnapshot;

public interface AttendanceTrackingService {

	void insert(AttendanceSnapshot snapshot);

	List<AttendanceSnapshot> list();
	
	void delete(AttendanceSnapshot snapshot);

	long getVisitorCount(Animal animal, Date from, Date to);

	Map<Animal, Long> getStatistics();
}