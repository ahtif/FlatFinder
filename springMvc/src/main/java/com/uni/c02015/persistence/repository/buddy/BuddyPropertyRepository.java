package com.uni.c02015.persistence.repository.buddy;

import com.uni.c02015.domain.User;
import com.uni.c02015.domain.buddy.BuddyProperty;
import com.uni.c02015.domain.property.Property;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuddyPropertyRepository extends CrudRepository<BuddyProperty, Integer> {

  BuddyProperty findByPropertyAndUser(Property property, User user);

  List<BuddyProperty> findByProperty(Property property);
}