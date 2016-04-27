package com.uni.c02015.domain.buddy;

import com.uni.c02015.domain.Searcher;

import javax.persistence.*;

@Entity
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @ManyToOne
  @JoinColumn(name = "senderId")
  private Searcher sender;
  
  @ManyToOne
  @JoinColumn(name = "receiverId")
  private Searcher receiver;

  private Boolean confirmed = false;
  
  public int getId() {
    return id;
  }

  public Searcher getReceiver() {
    return receiver;
  }

  public void setReceiver(Searcher receiver) {
    this.receiver = receiver;
  }

  public Searcher getSender() {
    return sender;
  }

  public void setSender(Searcher sender) {
    this.sender = sender;
  }

  public Boolean getConfirmed() {
    return confirmed;
  }

  public void setConfirmed(Boolean confirmed) {
    this.confirmed = confirmed;
  }

}