package com.ibm.itacademy.animaland.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AttendanceSnapshot;

@Stateful
public class JpaAttendanceTrackingService implements AttendanceTrackingService {
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Override
	public void insert(AttendanceSnapshot snapshot) {
		entityManager.persist(snapshot);
	}

	@Override
	public List<AttendanceSnapshot> list() {
		return entityManager
				.createQuery("SELECT s FROM AttendanceSnapshot s", AttendanceSnapshot.class)
				.getResultList();
	}

	@Override
	public void delete(AttendanceSnapshot snapshot) {
		entityManager.remove(snapshot);
	}

	@Override
	public long getVisitorCount(Animal animal, Date from, Date to) {
		String query = 
				"SELECT COUNT(s) FROM AttendanceSnapshot s"
				+ " WHERE s.animal = ?"
				+ " AND s.date BETWEEN :from AND :to";
		return entityManager
			.createQuery(query, Long.class)
			.setParameter("from", from)
			.setParameter("to", to)
			.getSingleResult();
	}

	@Override
	public Map<Animal, Long> getStatistics() {
		String query = "SELECT a, size(s.visitors) FROM AttendanceSnapshot AS s"
          + " JOIN s.animal AS a" 
          + " GROUP BY a";

		@SuppressWarnings("unchecked")
		List<Object[]> results = entityManager
				.createQuery(query)
				.getResultList();
		return toStatistics(results);
	}

	private Map<Animal, Long> toStatistics(List<Object[]> results) {
		Map<Animal, Long> statistics = new HashMap<Animal, Long>();
		for (Object[] row : results) {
			statistics.put((Animal) row[0], Long.valueOf((Integer) row[1]));
		}
		return statistics;
	}

}
