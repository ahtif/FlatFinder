package com.uni.c02015.domain.buddy;

import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;

import javax.persistence.*;

@Entity
@Table
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @ManyToOne
  @JoinColumn(name = "senderId")
  private User sender;
  
  @ManyToOne
  @JoinColumn(name = "receiverId")
  private User receiver;

  @ManyToOne
  @JoinColumn(name = "property")
  private Property property;

  private Boolean confirmed = false;

  /**
   * Get Id.
   * @return int
   */
  public int getId() {

    return id;
  }

  /**
   * Get receiver.
   * @return User
   */
  public User getReceiver() {

    return receiver;
  }

  /**
   * Set receiver.
   * @param receiver User
   */
  public void setReceiver(User receiver) {

    this.receiver = receiver;
  }

  /**
   * Get sender.
   * @return User
   */
  public User getSender() {

    return sender;
  }

  /**
   * Set the sender.
   * @param sender User
   */
  public void setSender(User sender) {

    this.sender = sender;
  }

  /**
   * Get confirmed.
   * @return Boolean
   */
  public Boolean getConfirmed() {

    return confirmed;
  }

  /**
   * Set confirmed.
   * @param confirmed Boolean
   */
  public void setConfirmed(Boolean confirmed) {

    this.confirmed = confirmed;
  }

  /**
   * Get property.
   * @return Property
   */
  public Property getProperty() {

    return property;
  }

  /**
   * Set property.
   * @param property Property
   */
  public void setProperty(Property property) {

    this.property = property;
  }
}