package com.ibm.itacademy.animaland;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DbUtilsAnimalDao implements AnimalDao {

	
	private QueryRunner queryRunner;

	public DbUtilsAnimalDao() {
		DataSource dataSource = DataSourceFactory
				.getInstance()
				.getDataSource();
		queryRunner = new QueryRunner(dataSource);
	}
	
	@Override
	public List<Animal> list() {
		try {
			String sql = "SELECT * FROM ANIMAL";
			return queryRunner.query(sql, 
					new BeanListHandler<Animal>(Animal.class));
		} catch (SQLException e) {
			throw new DatabaseException("Unable to list animals", e);
		}
	}

	@Override
	public Animal findById(Long id) {
		try {
			String sql = "SELECT * FROM ANIMAL WHERE id = ?";
			
			return queryRunner.query(sql, 
					new BeanHandler<Animal>(Animal.class),
					id);
		} catch (SQLException e) {
			throw new DatabaseException(
					"Unable to find animal by id " + id, e);
		}
		
	}

	@Override
	public void saveOrUpdate(Animal animal) {
		if (animal.getId() == null) {
			save(animal);
		} else {
			update(animal);
		}
	}

	private void update(Animal animal) {
		try {
			String sql = "UPDATE animal "
					+ " SET species = ?, age = ?, male = ?, diet = ?"
					+ " WHERE id = ?";
			queryRunner.update(sql, 
					animal.getSpecies(),
					animal.getAge(),
					animal.isMale(),
					animal.getDiet(),
					animal.getId()
					);
		} catch (SQLException e) {
			throw new DatabaseException("Unable to update animal " + animal.getId(),
					e);
		}
	}

	private void save(Animal animal) {
		try {
			String sql = "INSERT INTO animal (id, species, age, male, diet)"
					+ " VALUES(NULL, ?, ?, ?, ?)";
			
			Integer id = queryRunner.insert(sql, 
					new ScalarHandler<Integer>(),
					animal.getSpecies(),
					animal.getAge(),
					animal.isMale(),
					animal.getDiet()
					);
			animal.setId(id.longValue());
		} catch (SQLException e) {
			throw new DatabaseException("Unable to insert animal", e);
		}
		
	}

	
	
	
	
	
	

}
