package com.uni.c02015.domain.property;

import com.uni.c02015.domain.Landlord;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Property {

  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "landlord")
  private Landlord landlord;

  // String for this as numbers like 14a, 14b exist for flats!!
  private String number;
  private String street;
  private String postcode;
  private String city;

  @ManyToOne
  @JoinColumn(name = "type")
  private Type type;

  private Integer rooms;


  public Property() {

  }

  public Integer getId() {
    return id;
  }

  public Landlord getLandlord() {
    return landlord;
  }

  public void setLandlord(Landlord landlord) {
    this.landlord = landlord;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Integer getRooms() {
    return rooms;
  }

  public void setRooms(Integer rooms) {
    this.rooms = rooms;
  }
}