package com.ibm.itacademy.animaland.auth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.itacademy.animaland.DataSourceFactory;
import com.ibm.itacademy.animaland.DatabaseException;

public class DbUtilsLoginService implements LoginService {
	private static final Logger logger 
		= LoggerFactory.getLogger(DbUtilsLoginService.class);
	
	private static final String SQL_FILE = "/sql.xml";

	private QueryRunner queryRunner;
	private QueryLoader queryLoader;

	public DbUtilsLoginService() {
		DataSource dataSource = DataSourceFactory
				.getInstance()
				.getDataSource();
		this.queryRunner = new QueryRunner(dataSource);
		this.queryLoader = QueryLoader.instance();
	}
	
	@Override
	public boolean login(String account, String password) {
		try {
			String sql = getQuery("login");
			return this.queryRunner.query(sql, 
					new ScalarHandler<Boolean>(),
					account,
					password
					);
		} catch (SQLException e) {
			logger.error("Unable to login " + account, e);
			return false;
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
	
	
	
	
	
	
	
	
	
	
}
