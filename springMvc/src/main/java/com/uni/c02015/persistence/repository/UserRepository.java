package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {

  /**
   * Find by login.
   * @param login String
   * @return {@link User}
   */
  User findByLogin(String login);

  /**
   * Find by role.
   * @param role {@link Role}
   * @return {@link User} List
   */
  List<User> findByRole(Role role);
}