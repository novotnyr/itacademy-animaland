package com.ibm.itacademy.animaland;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DbUtilsAnimalDaoTest {
	private DbUtilsAnimalDao animalDao;

	@Before
	public void setUp() {
		animalDao = new DbUtilsAnimalDao();
	}
	
	@Test
	public void testList() throws Exception {
		assertEquals(2, animalDao.list().size());
	}
	
	@Test
	public void testFindById() throws Exception {
		assertNotNull(animalDao.findById(1L));
	}

	@Test
	public void testSave() throws Exception {
		Animal viper = new Animal();
		viper.setSpecies("viper");
		viper.setAge(2);
		viper.setDiet("rodents");
		viper.setMale(false);
		
		animalDao.saveOrUpdate(viper);
		
		assertNotNull(viper.getId());
	}
	
	
	
}
