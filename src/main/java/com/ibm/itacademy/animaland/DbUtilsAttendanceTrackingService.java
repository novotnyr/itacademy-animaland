package com.ibm.itacademy.animaland;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.hsqldb.jdbc.JDBCDataSource;

public class DbUtilsAttendanceTrackingService 
	implements AttendanceTrackingService 
{

	private static final String SQL_FILE = "/sql.xml";

	private static final String JDBC_PASSWORD = "";

	private static final String JDBC_LOGIN = "SA";

	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/zoo";

	private QueryRunner queryRunner;

	private QueryLoader queryLoader;
	
	public DbUtilsAttendanceTrackingService() {
		JDBCDataSource dataSource = new JDBCDataSource();
		dataSource.setUrl(JDBC_URL);
		dataSource.setUser(JDBC_LOGIN);
		dataSource.setPassword(JDBC_PASSWORD);
		
		queryRunner = new QueryRunner(dataSource);
		
		queryLoader = QueryLoader.instance();	
	}
	
	@Override
	public void insert(AttendanceSnapshot snapshot) {
		try {
			String snapshotInsertSql 
				= "INSERT INTO snapshot VALUES(?, ?, ?)";
			Integer id = queryRunner.insert(snapshotInsertSql, 
					new ScalarHandler<Integer>(), 
						null, 
						snapshot.getAnimal().getId(), 
						snapshot.getDate()
					);
			snapshot.setId(id.longValue());
			insertVisitors(snapshot);
			
		} catch (SQLException e) {
			throw new DatabaseException("Unable to insert snapshot",
					e);
		}
				
	}

	private void insertVisitors(AttendanceSnapshot snapshot) {
		try {
			String sql = "INSERT INTO snapshot_visitor VALUES(?, ?)";
			
			for (Visitor visitor : snapshot.getVisitors()) {
				queryRunner.update(sql, snapshot.getId(), visitor.getId());
			}
		} catch (SQLException e) {
			throw new DatabaseException(
					"Unable to insert snapshot visitors for ID" + snapshot.getId());
		}
	}

	@Override
	public List<AttendanceSnapshot> list() {
		try {
			String sql = "SELECT *"
					+ " FROM SNAPSHOT"
					+ " JOIN ANIMAL ON ANIMAL.ID = SNAPSHOT.ANIMAL_ID";
					
			List<AttendanceSnapshot> snapshots = queryRunner.query(sql, 
					new AttendanceSnapshotsResultSetHandler());
			for (AttendanceSnapshot snapshot : snapshots) {
				snapshot.setVisitors(findVisitors(snapshot));
			}
			
			return snapshots;
		} catch (SQLException e) {
			throw new DatabaseException("Unable to list snapshots", e);
		}
	}

	private List<Visitor> findVisitors(AttendanceSnapshot snapshot) {
		try {
			String sql = "SELECT id, type"
						+ " FROM visitor"
						+ " JOIN snapshot_visitor ON snapshot_visitor.visitor_id = visitor.id"
						+ " WHERE snapshot_id = ?";
			return queryRunner.query(sql, new VisitorsResultSetHandler(), snapshot.getId());
		} catch (SQLException e) {
			throw new DatabaseException(
					"Unable to find visitors for ID = " 
							+ snapshot.getId(), e);
		}
	}

	@Override
	public long getVisitorCount(Animal animal, Date from, Date to) {

		
		try {
			String sql = "SELECT COUNT(*) "
					+ " FROM SNAPSHOT "
					+ " JOIN SNAPSHOT_VISITOR ON SNAPSHOT.ID = SNAPSHOT_VISITOR.SNAPSHOT_ID"
					+ " WHERE ANIMAL_ID = ?"
					+ " AND TAKEN_AT BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";

			ScalarHandler<Long> handler = new ScalarHandler<Long>();
			
			long visitorCount = queryRunner.query(sql, handler, animal.getId(), from, to);
			// FIXME long cast to integer!
			return (int) visitorCount;		
		} catch (SQLException e) {
			throw new DatabaseException(
					"Unable to retrieve visitor count", e);
		}		
	}

	@Override
	public Map<Animal, Long> getStatistics() {
		try {
			String sql = getQuery("getStatistics");
			
			return queryRunner.query(sql, 
					new StatisticsResultHandler());
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get statistics", e);
		}
	}

	private String getQuery(String query) {
		try {
			Map<String, String> queries = queryLoader.load(SQL_FILE);
			return queries.get(query);
		} catch (IOException e) {
			throw new DatabaseException(
					"Unable to load SQL query " + query, e);
		}
	}

	@Override
	public void delete(AttendanceSnapshot snapshot) {
		try {
			String deleteSnapshot = "DELETE FROM snapshot WHERE id = ?";
			String deleteSnapshotVisitors
				= "DELETE FROM snapshot_visitor WHERE snapshot_id = ?";
			
			queryRunner.update(deleteSnapshotVisitors, snapshot.getId());
			queryRunner.update(deleteSnapshot, snapshot.getId());
		} catch (SQLException e) {
			throw new DatabaseException("Unable to delete snapshot", e);
		}
		
		
	}
	
	
	
	
	

}
