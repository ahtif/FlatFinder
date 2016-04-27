package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {

  User findByLogin(String login);

  User findByEmailAddress(String email);

  List<User> findByRole(Role role);

  User findById(Integer integer);
}