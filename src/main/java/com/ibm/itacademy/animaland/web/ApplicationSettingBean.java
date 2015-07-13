package com.ibm.itacademy.animaland.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationSettingBean {
	public String getVersion() {
		return "1.0.0";
	}
}
