package org.fd.repository;

import org.fd.model.User;
import org.springframework.data.domain.Sort;

/**
 * Contract for data access operations on {@link User}.
 */
public interface UserRepository extends ModelRepository<User>
{
  Sort DEFAULT_SORT = new Sort("firstName", "lastName", "emailAddress");

  /**
   * Finds a user having a specified email address.
   *
   * @param emailAddress The email address to find.
   * @return A {@link User} if the specified email address is found,
   * {@code null} otherwise.
   */
  User findByEmailAddress(String emailAddress);
}
