package com.ibm.itacademy.animaland;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;


public class StatisticsResultHandler
	implements ResultSetHandler<Map<Animal, Long>> {

	@Override
	public Map<Animal, Long> handle(ResultSet rs) throws SQLException {
		Map<Animal, Long> statistics = new LinkedHashMap<>();
		while(rs.next()) {
			Animal animal = new Animal();
			animal.setId(rs.getLong("id"));
			animal.setSpecies(rs.getString("species"));
			animal.setAge(rs.getInt("age"));
			animal.setMale(rs.getBoolean("male"));
			animal.setDiet(rs.getString("diet"));
			
			long visitorCount = rs.getLong("visitor_count");
			
			statistics.put(animal, visitorCount);
		}
			
		return statistics;
	}

	
	
	
	
	
	
	
}
