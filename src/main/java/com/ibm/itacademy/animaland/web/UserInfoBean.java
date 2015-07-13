package com.ibm.itacademy.animaland.web;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserInfoBean {
	@Inject
	private LoginBean login;

	public String getLoginInfo() {
		if(login.isAuthenticated()) {
			return "logged in";
		} else {
			return "anonymous";
		}
	}
}
