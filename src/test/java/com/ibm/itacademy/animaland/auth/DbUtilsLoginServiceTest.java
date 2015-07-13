package com.ibm.itacademy.animaland.auth;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DbUtilsLoginServiceTest {
	private DbUtilsLoginService loginService;

	@Before
	public void setUp() {
		this.loginService = new DbUtilsLoginService();
	}
	
	@Test
	public void testLogin() {
		assertTrue(this.loginService.login("admin", "admin"));
	}
	
	@Test
	public void testLoginWithIncorrectUsername() throws Exception {
		assertFalse(this.loginService.login("BADLOGIN!!", "BADPASSWORD!"));
	}
	
	
	
	
	
	
	
	
	
	
}
