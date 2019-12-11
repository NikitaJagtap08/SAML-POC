package org.fd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

/**
 * Represents a user.
 */
@Entity
@Table(name = "user")
public class User extends Model
{
  @Column(name = "email_address", unique = true)
  @NotNull
  @Size(max = 254, min = 6)
  private String emailAddress;

  @Column(name = "first_name")
  @NotNull
  @Size(max = 50, min = 1)
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  @Size(max = 50, min = 1)
  private String lastName;

  @Column(name = "password")
  @Size(max = 1000)
  private String password;

  /**
   * Default constructor.
   */
  public User()
  {
    super();
  }

  /**
   * Creates a user with a specified email address, first name and last name.
   *
   * @param emailAddress The user's email address.
   * @param firstName    The user's first name.
   * @param lastName     The user's last name.
   */
  public User(final String emailAddress, final String firstName, final String lastName)
  {
    this();

    setEmailAddress(emailAddress);
    setFirstName(firstName);
    setLastName(lastName);
  }

  /**
   * Gets the email address for the user.  Used to identify the user
   * uniquely.
   *
   * @return The email address for the user.
   */
  public String getEmailAddress()
  {
    return emailAddress;
  }

  /**
   * Gets the user's first name.
   *
   * @return The user's first name.
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets the user's last name.
   *
   * @return The user's last name.
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the full name for the user as a combination of their first and
   * last names.
   *
   * @return The user's full name.
   */
  public String getName()
  {
    return String.format("%s %s", firstName, lastName);
  }

  /**
   * Gets the authentication password for the user.
   *
   * @return The authentication password for the user.
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Sets the email address for the user.
   *
   * @param emailAddress The email address for the user.
   */
  public void setEmailAddress(final String emailAddress)
  {
    this.emailAddress = Optional.ofNullable(emailAddress).map(e -> e.trim().toLowerCase()).orElse(null);
  }

  /**
   * Sets the user's first name.
   *
   * @param firstName The user's first name.
   */
  public void setFirstName(final String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Sets the user's last name.
   *
   * @param lastName The user's last name.
   */
  public void setLastName(final String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Sets the authentication password for the user.
   *
   * @param password The authentication password for the user.
   */
  public void setPassword(final String password)
  {
    this.password = password;
  }
}
