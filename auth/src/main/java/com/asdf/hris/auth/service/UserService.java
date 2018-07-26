package com.asdf.hris.auth.service;

import com.asdf.hris.auth.model.User;

/**
 * @author markkelvinpineda
 *
 */
public interface UserService {
	void save(User user);
	
	User findByUsername(String username);

}
