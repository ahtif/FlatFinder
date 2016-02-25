package com.uni.c02015.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue
  private Integer id;
 
  @ManyToOne
  @JoinColumn(name = "parentId")
  private Message parent;
  
  @OneToMany(mappedBy = "parent")
  private Collection<Message> children;
  
  @ManyToOne
  @JoinColumn(name = "senderId")
  private User sender;
  
  @ManyToOne
  @JoinColumn(name = "receiverId")
  private User receiver;
  
  private String subject;
  
  private String message;
  
  private Date messageDate;
  
  private Boolean isRead;

  public Date getMessageDate() {
    return messageDate;
  }

  public void setMessageDate(Date messageDate) {
    this.messageDate = messageDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
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

  public Message getParent() {
    return parent;
  }

  public void setParent(Message parent) {
    this.parent = parent;
  }

  public Boolean getIsRead() {
    return isRead;
  }

  public void setIsRead(Boolean read) {
    this.isRead = read;
  }
  
  public Collection<Message> getChildren() {
    return children;
  }
  
  public void setChildren(Collection<Message> children) {
    this.children = children; 
  }
  
}