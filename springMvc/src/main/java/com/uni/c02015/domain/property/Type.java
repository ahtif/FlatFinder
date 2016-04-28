package com.uni.c02015.domain.property;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Type {

  @Id
  @GeneratedValue
  private Integer id;

  private String type;

  /**
   * Get Id.
   * @return Integer
   */
  public Integer getId() {

    return id;
  }

  /**
   * Get type.
   * @return Type
   */
  public String getType() {

    return type;
  }

  /**
   * Set type.
   * @param type Type
   */
  public void setType(String type) {

    this.type = type;
  }
}