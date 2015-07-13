package com.ibm.itacademy.animaland.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;

@Named
public class AnimalConverter implements Converter {

	@Inject
	private AnimalDao animalDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = Long.parseLong(value);
		return animalDao.findById(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Animal animal = (Animal) object;
		if(animal == null) {
			return null;
		}
		return Long.toString(animal.getId());
	}

}
