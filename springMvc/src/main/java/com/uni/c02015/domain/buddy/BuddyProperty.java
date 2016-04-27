package com.uni.c02015.domain.buddy;

import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;

import javax.persistence.*;

@Entity
@Table
public class BuddyProperty {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user")
  private User user;

  @ManyToOne
  @JoinColumn(name = "property")
  private Property property;

  public void setProperty(Property property) {

    this.property = property;
  }

  public Property getProperty() {

    return property;
  }

  public void setUser(User user) {

    this.user = user;
  }

  public User getUser() {

    return user;
  }
}
