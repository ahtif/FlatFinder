package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.User;
import com.uni.c02015.domain.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);
  
  List<VerificationToken> findByUser(User user);

}