package com.uni.c02015.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "role")
  private Role role;

  private String login;
  private String password;

  public User() {}

  /**
   * Constructor.
   * @param login String
   * @param password String
   * @param role {@link Role}
   */
  public User(String login, String password, Role role) {

    setLogin(login);
    setPassword(password);
    setRole(role);
  }

  /**
   * Get the user ID.
   * @return Integer
   */
  public Integer getId() {

    return id;
  }

  /**
   * Get the user login.
   * @return String
   */
  public String getLogin() {

    return login;
  }

  /**
   * Set the user login.
   * @param login String
   */
  public void setLogin(String login) {

    this.login = login;
  }

  /**
   * Get the use password.
   * @return String
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the user password.
   * @param password String
   */
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * Get the user role.
   * @return {@link Role}
   */
  public Role getRole() {

    return role;
  }

  /**
   * Set the user role.
   * @param role {@link Role}
   */
  public void setRole(Role role) {

    this.role = role;
  }
}