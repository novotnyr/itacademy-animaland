package com.ibm.itacademy.animaland;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AttendanceTrackingService {

	public abstract void insert(AttendanceSnapshot snapshot);

	public abstract List<AttendanceSnapshot> list();
	
	public void delete(AttendanceSnapshot snapshot);

	public abstract long getVisitorCount(Animal animal, Date from, Date to);

	public abstract Map<Animal, Long> getStatistics();
}