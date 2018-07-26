package com.asdf.hris.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asdf.hris.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
