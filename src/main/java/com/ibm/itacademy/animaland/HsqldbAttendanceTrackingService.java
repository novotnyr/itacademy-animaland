package com.ibm.itacademy.animaland;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Alternative
public class HsqldbAttendanceTrackingService implements AttendanceTrackingService {
	private static final String JDBC_PASSWORD = "";

	private static final String JDBC_LOGIN = "SA";

	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/zoo";
	
	private static final Logger logger 
		= LoggerFactory.getLogger(HsqldbAttendanceTrackingService.class);
	
	/* (non-Javadoc)
	 * @see com.ibm.itacademy.animaland.AttendanceTrackingService#insert(com.ibm.itacademy.animaland.AttendanceSnapshot)
	 */
	@Override
	public void insert(AttendanceSnapshot snapshot) {
		logger.debug("Inserting snapshot");
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager
					.getConnection(JDBC_URL, JDBC_LOGIN, JDBC_PASSWORD);
			statement 
			= connection
			.prepareStatement("INSERT INTO snapshot VALUES(NULL, ?, ?)");
			
			statement.setLong(1, snapshot.getAnimal().getId());
			statement.setTimestamp(2, 
					new Timestamp(snapshot.getDate().getTime()));
			
			statement.execute();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to insert snapshot data", e);
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					logger.error("Cannot close statement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error("Cannot close connection", e);
				}
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.itacademy.animaland.AttendanceTrackingService#list()
	 */
	@Override
	public List<AttendanceSnapshot> list() {
		ArrayList<AttendanceSnapshot> snapshots = new ArrayList<AttendanceSnapshot>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection 
			= DriverManager.getConnection(
					JDBC_URL, JDBC_LOGIN, JDBC_PASSWORD);
			statement = connection.createStatement();
			resultSet 
			= statement.executeQuery("SELECT * FROM snapshot");
			while(resultSet.next()) {
				AttendanceSnapshot snapshot = new AttendanceSnapshot();
				snapshot.setDate(resultSet.getDate("taken_at"));
				
				snapshots.add(snapshot);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Unable to retrieve snapshot data", e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}			
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException | NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.trace("Found " + snapshots.size() + " snapshots");
		
		return snapshots;
	}
	
	public long getVisitorCount(Animal animal, Date since, Date to) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(
					JDBC_URL, JDBC_LOGIN, JDBC_PASSWORD);
			String sql = "SELECT COUNT(*) "
					+ " FROM SNAPSHOT "
					+ " JOIN SNAPSHOT_VISITOR ON SNAPSHOT.ID = SNAPSHOT_VISITOR.SNAPSHOT_ID"
					+ " WHERE ANIMAL_ID = ?"
					+ " AND TAKEN_AT BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, animal.getId());
			statement.setTimestamp(2, new Timestamp(since.getTime()));
			statement.setTimestamp(3, new Timestamp(to.getTime()));
			
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}			
		} catch (SQLException e) {
			throw new DatabaseException("Unable to retrieve data", e);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	@Override
	public Map<Animal, Long> getStatistics() {
		// FIXME implement properly
		return new HashMap<>();
	}

	@Override
	public void delete(AttendanceSnapshot snapshot) {
		// FIXME implement		
	}
	
}
