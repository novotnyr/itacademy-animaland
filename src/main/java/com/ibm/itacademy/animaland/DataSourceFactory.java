package com.ibm.itacademy.animaland;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public class DataSourceFactory {
	private static final DataSourceFactory INSTANCE
		=	new DataSourceFactory();
	
	private static final String JDBC_PASSWORD = "";

	private static final String JDBC_LOGIN = "SA";

	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/zoo";

	private JDBCDataSource dataSource;
	
	private DataSourceFactory() {
		// ensures singleton instance
	}
	
	public DataSource getDataSource() {
		if (this.dataSource == null) {
			this.dataSource = new JDBCDataSource();
			this.dataSource.setUrl(JDBC_URL);
			this.dataSource.setUser(JDBC_LOGIN);
			this.dataSource.setPassword(JDBC_PASSWORD);
		}
		return this.dataSource;
	}
	
	public static DataSourceFactory getInstance() {
		return INSTANCE;
	}
	
	
	
	
	
	
}
