package com.uni.c02015.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {

  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;

  @Enumerated(EnumType.STRING)
  private TokenType type;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  private Date expiryDate;

  private boolean used = false;

  /**
   * Constructor for a verification token, takes a token, string and type and creates a new token.
   *
   * @param token The unique token.
   * @param user  The user for the token.
   * @param type  The type of token.
   */
  public VerificationToken(final String token, final User user, final TokenType type) {

    super();

    this.token = token;
    this.user = user;
    this.type = type;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  /**
   * Get token.
   *
   * @return String
   */
  public String getToken() {

    return token;
  }

  /**
   * Set token.
   *
   * @param token String
   */
  public void setToken(final String token) {

    this.token = token;
  }

  /**
   * Get user.
   *
   * @return User
   */
  public User getUser() {

    return user;
  }

  /**
   * Set user.
   *
   * @param user User
   */
  public void setUser(final User user) {

    this.user = user;
  }

  /**
   * Get expiry date.
   *
   * @return Date
   */
  public Date getExpiryDate() {

    return expiryDate;
  }

  /**
   * Set expiry date.
   *
   * @param expiryDate Date
   */
  public void setExpiryDate(final Date expiryDate) {

    this.expiryDate = expiryDate;
  }

  /**
   * Get is used.
   *
   * @return boolean
   */
  public boolean isUsed() {

    return used;
  }

  /**
   * Set used.
   *
   * @param used boolean
   */
  public void setUsed(boolean used) {

    this.used = used;
  }

  /**
   * Calculate expiration date.
   *
   * @param expiryTimeInMinutes Expiry time
   * @return Date
   */
  private Date calculateExpiryDate(final int expiryTimeInMinutes) {

    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(new Date().getTime());
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);

    return new Date(cal.getTime().getTime());
  }

  /**
   * Update token.
   *
   * @param token String
   */
  public void updateToken(final String token) {

    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  /**
   * Get type.
   *
   * @return TokenType
   */
  public TokenType getType() {

    return type;
  }

  /**
   * Set type.
   *
   * @param type TokenType
   */
  public void setType(TokenType type) {

    this.type = type;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
    result = prime * result + ((token == null) ? 0 : token.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());

    return result;
  }

  @Override
  public boolean equals(final Object obj) {

    if (this == obj) {

      return true;
    }

    if (obj == null) {

      return false;
    }

    if (getClass() != obj.getClass()) {

      return false;
    }

    final VerificationToken other = (VerificationToken) obj;

    if (expiryDate == null) {

      if (other.expiryDate != null) {

        return false;
      }

    } else if (!expiryDate.equals(other.expiryDate)) {

      return false;
    }

    if (token == null) {

      if (other.token != null) {

        return false;
      }

    } else if (!token.equals(other.token)) {

      return false;
    }

    if (user == null) {

      if (other.user != null) {

        return false;
      }

    } else if (!user.equals(other.user)) {

      return false;
    }

    if (type == null) {

      if (other.type != null) {

        return false;
      }

    } else if (!(type == other.type)) {

      return false;
    }

    return true;
  }

  @Override
  public String toString() {

    final StringBuilder builder = new StringBuilder();
    builder.append("Token [String=").append(token).append("]")
        .append("[Expires").append(expiryDate).append("]").append("[Type=").append(type);

    return builder.toString();
  }
}