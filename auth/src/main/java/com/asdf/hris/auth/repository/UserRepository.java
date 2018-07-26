package com.asdf.hris.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asdf.hris.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String name);

}
