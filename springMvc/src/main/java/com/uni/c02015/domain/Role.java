package com.uni.c02015.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Role {

  @Id
  private Integer id;

  @OneToMany
  @JoinColumn(name = "users")
  private Set<User> users;

  private String role;

  /**
   * Constructor.
   */
  public Role() {}

  /**
   * Constructor.
   * @param role String
   */
  public Role(String role) {

    setRole(role);
  }

  /**
   * Get the role ID.
   * @return Integer
   */
  public Integer getId() {

    return id;
  }

  /**
   * Set the role ID.
   * @param id Integer
   */
  public void setId(Integer id) {

    this.id = id;
  }

  /**
   * Get the role.
   * @return String
   */
  public String getRole() {

    return role;
  }

  /**
   * Set the role.
   * @param role String
   */
  public void setRole(String role) {

    this.role = role;
  }

  /**
   * Get users who have the role.
   * @return {@link User} Set
   */
  public Set<User> getUsers() {

    return users;
  }

  /**
   * Set users who have the role.
   * @param users {@link User} Set
   */
  public void setUsers(Set<User> users) {

    this.users = users;
  }
}