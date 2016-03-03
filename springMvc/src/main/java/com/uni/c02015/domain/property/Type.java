package com.uni.c02015.domain.property;

import javax.persistence.*;

@Entity
@Table
public class Type {

  @Id
  @GeneratedValue
  private Integer id;

  private String type;

  public Integer getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
