package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends CrudRepository<Role, Integer> {

  /**
   * Find a role by ID.
   * @param id Integer
   * @return {@link Role}
   */
  Role findById(Integer id);
}