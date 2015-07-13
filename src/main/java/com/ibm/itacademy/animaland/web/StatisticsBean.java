package com.ibm.itacademy.animaland.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;
import com.ibm.itacademy.animaland.AttendanceTrackingService;

@Named
public class StatisticsBean {
	@Inject
	private AnimalDao animalDao;
	
	@Inject
	private AttendanceTrackingService attendanceTrackingService;
	
	public int getAnimalCount() {
		return animalDao.list().size();
	}
	
	public List<AnimalStatistics> getStatistics() {
		Map<Animal, Long> statistics = attendanceTrackingService.getStatistics();
		List<AnimalStatistics> list = new ArrayList<>();
		for (Entry<Animal, Long> entry : statistics.entrySet()) {
			list.add(new AnimalStatistics(entry.getKey(), 
					entry.getValue()));
		}
		return list;
	}
	
	public class AnimalStatistics {
		private Animal animal;
		
		private Long visitorCount;

		public AnimalStatistics(Animal animal, Long visitorCount) {
			super();
			this.animal = animal;
			this.visitorCount = visitorCount;
		}
		
		public Animal getAnimal() {
			return animal;
		}
		
		public Long getVisitorCount() {
			return visitorCount;
		}
		
		
		
		
	}
	
	
}
