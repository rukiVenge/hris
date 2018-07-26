package com.asdf.hris.auth.service;

/**
 * @author markkelvinpineda
 *
 */
public interface SecurityService {
	    
	public String findLoggedInUsername();
	public void autologin(String username, String password);
}
