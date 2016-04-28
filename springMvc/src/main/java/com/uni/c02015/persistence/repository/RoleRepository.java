package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends CrudRepository<Role, Integer> {

  Role findById(Integer id);
  
  Role findByRole(String role);
}