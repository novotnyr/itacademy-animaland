package com.ibm.itacademy.animaland;

public class AnimalDaoFactory {
	private static final AnimalDaoFactory INSTANCE
		= new AnimalDaoFactory();
	
	private DbUtilsAnimalDao animalDao;
	
	private AnimalDaoFactory() {
		// enforces singleton factory
	}
	
	public static AnimalDaoFactory getInstance() {
		return INSTANCE;
	}
	
	public AnimalDao getAnimalDao() {
		if (this.animalDao == null) {
			this.animalDao = new DbUtilsAnimalDao();			
		}
		return this.animalDao;
	}
}
