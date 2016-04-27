package com.uni.c02015.domain.property;

import com.uni.c02015.domain.Landlord;

import javax.persistence.*;
import java.util.Date;

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
  
  @ManyToOne
  @JoinColumn(name = "type")
  private Type type;

  private Integer rooms;
  private Integer pricePerMonth;

  @Temporal(TemporalType.DATE)
  private Date validFrom;
  @Temporal(TemporalType.DATE)
  private Date validTo;

  /**
   * Constructor.
   */
  public Property() {

  }

  /**
   * Get valid from date.
   * @return Date
   */
  public Date getValidFrom() {

    return validFrom;
  }

  /**
   * Set valid from date.
   * @param validFrom Date
   */
  public void setValidFrom(Date validFrom) {

    this.validFrom = validFrom;
  }

  /**
   * Get valid to date.
   * @return Date
   */
  public Date getValidTo() {

    return validTo;
  }

  /**
   * Set valid to date.
   * @param validTo Date
   */
  public void setValidTo(Date validTo) {

    this.validTo = validTo;
  }

  /**
   * Get price per month.
   * @return Integer
   */
  public Integer getPricePerMonth() {

    return pricePerMonth;
  }

  /**
   * Set price per month.
   * @param pricePerMonth Integer
   */
  public void setPricePerMonth(Integer pricePerMonth) {

    this.pricePerMonth = pricePerMonth;
  }

  /**
   * Get id.
   * @return Integer
   */
  public Integer getId() {

    return id;
  }

  /**
   * Get the landlord.
   * @return Landlord
   */
  public Landlord getLandlord() {

    return landlord;
  }

  /**
   * Set the landlord.
   * @param landlord Landlord
   */
  public void setLandlord(Landlord landlord) {

    this.landlord = landlord;
  }

  /**
   * Get number.
   * @return String
   */
  public String getNumber() {

    return number;
  }

  /**
   * Set number.
   * @param number String
   */
  public void setNumber(String number) {

    this.number = number;
  }

  /**
   * Get street.
   * @return String
   */
  public String getStreet() {

    return street;
  }

  /**
   * Set street.
   * @param street String
   */
  public void setStreet(String street) {

    this.street = street;
  }

  /**
   * Get postcode.
   * @return String
   */
  public String getPostcode() {

    return postcode;
  }

  /**
   * Set postcode.
   * @param postcode String
   */
  public void setPostcode(String postcode) {

    this.postcode = postcode;
  }

  /**
   * Get city.
   * @return String
   */
  public String getCity() {

    return city;
  }

  /**
   * Set city.
   * @param city String
   */
  public void setCity(String city) {

    this.city = city;
  }

  /**
   * Get type.
   * @return Type
   */
  public Type getType() {

    return type;
  }

  /**
   * Set type.
   * @param type Type
   */
  public void setType(Type type) {

    this.type = type;
  }

  /**
   * Get rooms.
   * @return Integer
   */
  public Integer getRooms() {

    return rooms;
  }

  /**
   * Set rooms.
   * @param rooms Integer
   */
  public void setRooms(Integer rooms) {

    this.rooms = rooms;
  }

  /**
   * Get latitude.
   * @return double
   */
  public double getLatitude() {

    return latitude;
  }

  /**
   * Set latitude.
   * @param latitude double
   */
  public void setLatitude(double latitude) {

    this.latitude = latitude;
  }

  /**
   * Get longitude.
   * @return double
   */
  public double getLongitude() {

    return longitude;
  }

  /**
   * Set longitude.
   * @param longitude double
   */
  public void setLongitude(double longitude) {

    this.longitude = longitude;
  }
}