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
  
  public int getId() {
    return id;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public Boolean getConfirmed() {
    return confirmed;
  }

  public void setConfirmed(Boolean confirmed) {
    this.confirmed = confirmed;
  }

  public Property getProperty() {
    return property;
  }

  public void setProperty(Property property) {
    this.property = property;
  }
}