package com.uni.c02015.domain.property;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
  private double latitude;
  private double longitude;
  
  @OneToMany
  @JoinColumn
  private Set<User> interestedUsers;

  @ManyToOne
  @JoinColumn(name = "type")
  private Type type;

  private Integer rooms;
  private Integer pricePerMonth;

  @Temporal(TemporalType.DATE)
  Date validFrom;
  @Temporal(TemporalType.DATE)
  Date validTo;

  public Property() {

  }

  public Date getValidFrom() {

    return validFrom;
  }

  public void setValidFrom(Date validFrom) {

    this.validFrom = validFrom;
  }

  public Date getValidTo() {

    return validTo;
  }

  public void setValidTo(Date validTo) {

    this.validTo = validTo;
  }

  public Integer getPricePerMonth() {

    return pricePerMonth;
  }

  public void setPricePerMonth(Integer pricePerMonth) {

    this.pricePerMonth = pricePerMonth;
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

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
  
  public Set<User> getInterestedUsers() {
    return interestedUsers;
  }

  public void setInterestedUsers(Set<User> interestedUsers) {
    this.interestedUsers = interestedUsers;
  }
}