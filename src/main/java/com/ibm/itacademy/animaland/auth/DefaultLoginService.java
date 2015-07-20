package com.ibm.itacademy.animaland.auth;


public class DefaultLoginService implements LoginService {
	public static final String ACCOUNT = "admin";

	public static final String PASSWORD = "admin";
		
	
	@Override
	public boolean login(String account, String password) {
		return ACCOUNT.equals(account) && PASSWORD.equals(password);
	}

}
