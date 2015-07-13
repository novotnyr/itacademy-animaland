package com.ibm.itacademy.animaland;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.inject.Alternative;

@Alternative
public class InMemoryAttendanceTrackingService 
	implements AttendanceTrackingService {
	
	private List<AttendanceSnapshot> snapshots
		= new CopyOnWriteArrayList<AttendanceSnapshot>();

	@Override
	public void insert(AttendanceSnapshot snapshot) {
		snapshots.add(snapshot);
	}

	@Override
	public List<AttendanceSnapshot> list() {
		return snapshots;
	}

	@Override
	public long getVisitorCount(Animal animal, Date from, Date to) {
		return 0;
	}

	@Override
	public Map<Animal, Long> getStatistics() {
		// FIXME implement properly
		return new HashMap<>();
	}

	@Override
	public void delete(AttendanceSnapshot snapshot) {
		// FIXME implement me
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
