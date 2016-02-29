package com.uni.c02015.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Property {

  @Id
  @GeneratedValue
  private Integer id;

  public Property() {

  }

  public Integer getId() {
    return id;
  }
}