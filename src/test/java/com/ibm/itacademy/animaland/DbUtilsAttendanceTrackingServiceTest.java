package com.ibm.itacademy.animaland;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

public class DbUtilsAttendanceTrackingServiceTest 
	extends AttendanceTrackingServiceTest {
	
	private DbUtilsAttendanceTrackingService service;
	private QueryRunner queryRunner;

	@Override
	public void setUp() {
		super.setUp();

		service = new DbUtilsAttendanceTrackingService();
		queryRunner = new QueryRunner(dataSource);

	} 

	@Test
	public void testList() {
		
		List<AttendanceSnapshot> snapshots = service.list();
		for(AttendanceSnapshot snapshot : snapshots) {
			System.out.println(snapshot);
		}
		
		assertEquals(2, snapshots.size());
	}

	@Test
	public void testInsert() throws SQLException {
		DbUtilsAttendanceTrackingService service
			= new DbUtilsAttendanceTrackingService();
		
		service.insert(snapshot);
		
		long count = queryRunner.query("SELECT COUNT(*) FROM snapshot WHERE id = ?", 
				new ScalarHandler<Long>(), snapshot.getId());
		Assert.assertEquals(1L, count);
	}
	
	
	
	
	
	
	
	
	
	
	
}
