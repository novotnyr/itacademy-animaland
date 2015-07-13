package com.ibm.itacademy.animaland;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.util.Date;

import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.Before;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

public abstract class AttendanceTrackingServiceTest {

	private static final String JDBC_PASSWORD = "";
	private static final String JDBC_LOGIN = "SA";
	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/zoo";
	protected Animal animal;
	protected HsqldbAttendanceTrackingService attendanceService;
	protected AttendanceSnapshot snapshot;
	protected JDBCDataSource dataSource;

	@Before
	public void setUp() {
		prepareFixedData();
		prepareDatabase();
	}

	private void prepareFixedData() {
		animal = new Animal();
		animal.setId(1L);
		animal.setSpecies("elephant");
		animal.setMale(false);
		animal.setAge(56);
		animal.setDiet("vegetables");		
	
		Visitor visitor = new Visitor();
		visitor.setId(1L);
		visitor.setType(VisitorType.ADULT);
		
		Visitor visitor2 = new Visitor();
		visitor2.setId(1L);
		visitor2.setType(VisitorType.ADULT);
		
		Visitor visitor3 = new Visitor();
		visitor3.setId(2L);
		visitor3.setType(VisitorType.CHILD);
		
		attendanceService = new HsqldbAttendanceTrackingService();
		
		snapshot = new AttendanceSnapshot();
		snapshot.setAnimal(animal);
		snapshot.setDate(new Date(115, 5, 23));
		snapshot.add(visitor);
		snapshot.add(visitor2);
		snapshot.add(visitor3);
	}

	private void prepareDatabase() {
		Operation script = sequenceOf(
				deleteAllFrom(
						"animal", 
						"snapshot", 
						"visitor", 
						"snapshot_visitor"),
						
				insertInto("animal")
					.columns("id", "species", "age", "male", "diet")
					.values(1, "Elephant", 56, 0, "vegetables")
					.values(2, "Tiger", 9, 1, "meat")
					.build(),
					
				insertInto("visitor")
					.columns("id", "type")
					.values(1, "ADULT")
					.values(2, "CHILD")
					.build(),
	
				insertInto("snapshot")
					.columns("id", "animal_id", "taken_at")
					.values(1,1, new Date(115, 5, 23, 11, 10))
					.values(2,2, new Date(115, 5, 22, 7, 30))
					.build(),
			
				insertInto("snapshot_visitor")
					.columns("snapshot_id", "visitor_id")
					.values(1,1)
					.values(1,2)
					.values(2,1)
					.build()
		);
		
		dataSource = new JDBCDataSource();
		dataSource.setUrl(JDBC_URL);
		dataSource.setUser(JDBC_LOGIN);
		dataSource.setPassword(JDBC_PASSWORD);
		
		DbSetup dbSetup = new DbSetup(
				new DataSourceDestination(dataSource), 
				script);
		dbSetup.launch();
	}

}