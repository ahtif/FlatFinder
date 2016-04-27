package com.uni.c02015.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Searcher {

  @Id
  private Integer id;
  private String firstName;
  private String lastName;
  private boolean buddyPref;
  
  public Searcher(int id) {

    this.id = id;
  }

  public Searcher() {

  }
  
  public void setId(int id) {
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

  public boolean getBuddyPref() {
    return buddyPref;
  }

  public void setBuddyPref(boolean buddyPref) {
    this.buddyPref = buddyPref;
  }
  
}