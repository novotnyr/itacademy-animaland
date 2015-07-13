package com.ibm.itacademy.animaland;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class AttendanceSnapshotsResultSetHandler 
	implements ResultSetHandler<List<AttendanceSnapshot>>{

	@Override
	public List<AttendanceSnapshot> handle(ResultSet rs) throws SQLException {
		List<AttendanceSnapshot> snapshots = 
				new ArrayList<AttendanceSnapshot>();
		
		while(rs.next()) {
			AttendanceSnapshot snapshot = new AttendanceSnapshot();
			snapshot.setId(rs.getLong(1));
			snapshot.setDate(new Date(rs.getTimestamp(3).getTime()));
			
			Animal animal = new Animal();
			animal.setId(rs.getLong(2));
			animal.setSpecies(rs.getString(5));
			animal.setAge(rs.getInt(6));
			animal.setMale(rs.getBoolean(7));
			animal.setDiet(rs.getString(8));
			
			snapshot.setAnimal(animal);
			
			snapshots.add(snapshot);
		}
		return snapshots;
	}
	
	

}
