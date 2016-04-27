package com.uni.c02015.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Searcher {

  @Id
  private Integer id;
  private String firstName;
  private String lastName;
  private boolean buddyPref;

  /**
   * Constructor.
   * @param id int
   */
  public Searcher(int id) {

    this.id = id;
  }

  /**
   * Constructor.
   */
  public Searcher() {

  }

  /**
   * Set the id of the searcher.
   * @param id int
   */
  public void setId(int id) {

    this.id = id;
  }

  /**
   * Get the Id.
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
   * @param firstName
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

  /**
   * Get buddy preference.
   * @return boolean
   */
  public boolean getBuddyPref() {

    return buddyPref;
  }

  /**
   * Set buddy preference.
   * @param buddyPref boolean
   */
  public void setBuddyPref(boolean buddyPref) {

    this.buddyPref = buddyPref;
  }
}