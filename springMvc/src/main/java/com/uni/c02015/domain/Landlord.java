package com.uni.c02015.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Landlord {

  //login details
  @Id
  private Integer id;
  
  //personal details
  private String firstName;
  private String lastName;


  public Landlord() {

  }

  public Landlord(int id) {

    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}