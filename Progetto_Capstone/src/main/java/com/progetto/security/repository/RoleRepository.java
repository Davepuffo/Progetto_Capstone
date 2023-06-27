package com.progetto.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progetto.security.entity.ERole;
import com.progetto.security.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(ERole roleName);

}
