package com.ibm.itacademy.animaland.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ibm.itacademy.animaland.Animal;
import com.ibm.itacademy.animaland.AnimalDao;

@Path("/animals")
public class AnimalResource {
	@Inject
	private AnimalDao animalDao;

	@GET
	@Produces("application/json")
	public List<Animal> getAnimals() {
		return animalDao.list();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Animal findById(@PathParam("id") Long id) {
		return animalDao.findById(id);
	}
	
	@POST
	@Consumes("application/json")
	public Response add(Animal animal, @Context UriInfo uriInfo) {
		animalDao.saveOrUpdate(animal);
		
		URI uri = uriInfo
			.getAbsolutePathBuilder()
			.path(Long.toString(animal.getId()))
			.build();
			
		return Response
			.created(uri)
			.build();
	}
	
	@Path("/{id}")
	@DELETE
	public Response delete(@PathParam("id") Long id) {
		Animal animal = animalDao.findById(id);
		animalDao.delete(animal);
		
		return Response.ok().build();
	}
	
	@Path("/{id}")
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Animal update(@PathParam("id") Long id, Animal animal) {
		animal.setId(id);
		animalDao.saveOrUpdate(animal);
		
		return animalDao.findById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
