package org.fd.service;

import java.util.List;

import org.fd.model.User;
import org.fd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business logic operations for {@link User}.
 */
@Service
@Transactional(readOnly = true)
public class UserService
{
  @Autowired
  private UserRepository repository;

  /**
   * Authenticates a user using an email address and a password.  The email
   * address is used to look up the user and the provided password is
   * compared with that stored for the user.
   *
   * @param emailAddress The email address for the user to authenticate.
   * @param password     The password to use to authenticate.
   * @return {@code true} if a user with the specified email address exists
   * and has the specified password.
   */
  public User authenticate(final String emailAddress, final String password)
  {
    if (emailAddress == null)
    {
      // A user cannot be authenticated without the email address.
      throw new IllegalArgumentException(Errors.Authentication.BLANK_EMAIL);
    }

    if (password == null)
    {
      // A user cannot be authenticated without the password.
      throw new IllegalArgumentException(Errors.Authentication.BLANK_PASSWORD);
    }

    // Attempt to locate the user with the specified email address, making
    // sure that a case-insensitive search is performed.
    final User user = repository.findByEmailAddress(emailAddress.trim().toLowerCase());

    if (user == null)
    {
      // No user with the specified email address was found.
      throw new IllegalArgumentException(Errors.Authentication.UNKNOWN_EMAIL);
    }

    if (!user.getPassword().equals(password.trim()))
    {
      // The password did not match.
      throw new IllegalArgumentException(Errors.Authentication.PASSWORD_MISMATCH);
    }

    return user;
  }

  /**
   * Gets all users registered with the application.
   *
   * @return A {@link List} of {@link User}s.
   */
  public List<User> list()
  {
    return (List) repository.findAll(UserRepository.DEFAULT_SORT);
  }

  /**
   * Registers a user with specified information.
   *
   * @param user The user to register.
   */
  @Transactional
  public void register(final User user)
  {
    // Ensure that a user with the provided email address does not exist
    // already.
    if (repository.findByEmailAddress(user.getEmailAddress()) != null)
    {
      throw new IllegalArgumentException(Errors.Registration.DUPLICATE_EMAIL);
    }

    // Save the user.
    repository.save(user);
  }

  /**
   * Defines errors that may be encountered during processing.
   */
  private static final class Errors
  {
    /**
     * Defines errors that may be encountered when authenticating a user.
     */
    private static final class Authentication
    {
      static final String BLANK_EMAIL       = "authentication.email.blank";
      static final String BLANK_PASSWORD    = "authentication.password.blank";
      static final String PASSWORD_MISMATCH = "authentication.password.mismatch";
      static final String UNKNOWN_EMAIL     = "authentication.email.unknown";
    }

    /**
     * Defines errors that may be encountered when registering a user.
     */
    private static final class Registration
    {
      static final String DUPLICATE_EMAIL = "registration.email.duplicate";
    }
  }
}
