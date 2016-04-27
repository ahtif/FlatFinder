package com.uni.c02015.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Landlord {

  @Id
  private Integer id;
  
  //personal details
  private String firstName;
  private String lastName;

  /**
   * Constructor.
   */
  public Landlord() {

  }

  /**
   * Constructor.
   * @param id int
   */
  public Landlord(int id) {

    this.id = id;
  }

  /**
   * Get id.
   * @return Integer
   */
  public Integer getId() {

    return id;
  }

  /**
   * Get first name.
   * @return String
   */
  public String getFirstName() {

    return firstName;
  }

  /**
   * Set first name.
   * @param firstName String
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * Get last name.
   * @return String
   */
  public String getLastName() {

    return lastName;
  }

  /**
   * Set last name.
   * @param lastName String
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }
}