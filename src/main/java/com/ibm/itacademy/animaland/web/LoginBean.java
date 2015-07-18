package com.ibm.itacademy.animaland.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.itacademy.animaland.auth.LoginService;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	@Inject
	private transient LoginService loginService;
	
	private String login;
	
	private String password;
	
	private boolean isAuthenticated;
	
	public String authenticate() {
		if(loginService.login(login, password)) {
			
			System.out.println("Authenticated...");
			
			isAuthenticated = true;
			password = null;
			return "animals?faces-redirect=true";
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Access denied", 
						"Incorrect login or password"));
		
		return null;
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		
		return "login?faces-redirect=true";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	
	
	
	
	
	
	

}
