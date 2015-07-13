package com.ibm.itacademy.animaland;

import static org.junit.Assert.*;
import static com.ninja_squad.dbsetup.Operations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Insert;

public class HsqldbAttendanceTrackingServiceTest extends AttendanceTrackingServiceTest {
	@Test
	public void testInsert() {
		List<AttendanceSnapshot> snapshots = attendanceService.list();
		attendanceService.insert(snapshot);

		List<AttendanceSnapshot> updatedSnapshots = attendanceService.list();
		
		assertEquals(updatedSnapshots.size() - 1, snapshots.size());
	}
	
	@Test
	public void testGetVisitorCount() {
		attendanceService.insert(snapshot);
		
		Date since = new Date(/*2015*/ 115, /*June*/5, 22);
		Date to = new Date(115, 5, 24);
		long visitorCount = attendanceService.getVisitorCount(animal, since, to);	
	
		Assert.assertEquals(2, visitorCount);
	}
	
	@Test
	public void testGetVisitorCountForYear2000() {
		attendanceService.insert(snapshot);
		Date since = new Date(100, 0, 1);
		Date to = new Date(100, 11, 31);
		
		Assert.assertEquals(0, 
				attendanceService.getVisitorCount(animal, since, to));
	}
	
	@Test
	public void testList() {
		List<AttendanceSnapshot> snapshots = attendanceService.list();
		
		System.out.println(snapshots);
		
		Assert.assertEquals(2, snapshots.size());
	}
	
	
	
	
	
	

}
