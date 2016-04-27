package com.uni.c02015.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
 
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
  
  private String senderName;
  
  private String subject;
  
  @Column(name = "message", length = 512)
  private String message;
  
  private Date messageDate;
  
  private Boolean isRead;

  /**
   * Get Id.
   * @return int
   */
  public int getId() {

    return id;
  }

  /**
   * Get message date.
   * @return Date
   */
  public Date getMessageDate() {

    return messageDate;
  }

  /**
   * Set message date.
   * @param messageDate Date
   */
  public void setMessageDate(Date messageDate) {

    this.messageDate = messageDate;
  }

  /**
   * Get message.
   * @return String
   */
  public String getMessage() {

    return message;
  }

  /**
   * Set message.
   * @param message String
   */
  public void setMessage(String message) {

    this.message = message;
  }

  /**
   * Get subject.
   * @return String
   */
  public String getSubject() {

    return subject;
  }

  /**
   * Set subject.
   * @param subject String
   */
  public void setSubject(String subject) {

    this.subject = subject;
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
   * Set sender.
   * @param sender User
   */
  public void setSender(User sender) {

    this.sender = sender;
  }

  /**
   * Get parent message.
   * @return Message
   */
  public Message getParent() {

    return parent;
  }

  /**
   * Set parent message.
   * @param parent Message
   */
  public void setParent(Message parent) {

    this.parent = parent;
  }

  /**
   * Get is read.
   * @return Boolean
   */
  public Boolean getIsRead() {

    return isRead;
  }

  /**
   * Set is read.
   * @param read Boolean
   */
  public void setIsRead(Boolean read) {

    this.isRead = read;
  }

  /**
   * Get the children messages of the message.
   * @return Collection Message
   */
  public Collection<Message> getChildren() {

    return children;
  }

  /**
   * Set the children of the message.
   * @param children Collection Message
   */
  public void setChildren(Collection<Message> children) {

    this.children = children; 
  }

  /**
   * Get sender name.
   * @return String
   */
  public String getSenderName() {

    return senderName;
  }

  /**
   * Set sender name.
   * @param senderName String
   */
  public void setSenderName(String senderName) {

    this.senderName = senderName;
  }
}