package org.fd.security;

import org.fd.model.User;
import org.fd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

/**
 * An implementation of Spring Security {@link AuthenticationProvider} that
 * authenticates a client using an email address and a password.
 */
public class EmailAddressAuthenticationProvider implements AuthenticationProvider
{
  private static       Logger LOGGER = LoggerFactory.getLogger(EmailAddressAuthenticationProvider.class);
  private static final String ROLE   = "User";

  @Autowired
  private UserService userService;

  /**
   * Processes authentication information and attempts to authenticate the user.
   */
  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException
  {
    try
    {
      LOGGER.debug(String.format("Attempting to authenticate principal [%s].", authentication.getPrincipal()));

      // Attempt to authenticate the user.
      final User user = userService.authenticate(authentication.getName(), (String) authentication.getCredentials());

      LOGGER.debug(String.format("Principal [%s] authenticated successfully.", authentication.getPrincipal()));

      LOGGER.debug(String.format("Setting profile information for principal [%s].", authentication.getPrincipal()));

      // Generate an authentication token using the authenticated information.
      final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal()
          , authentication.getCredentials()
          , Collections.singletonList(new SimpleGrantedAuthority(ROLE)));

      // Save the user's full name so that it can be used in the presentation
      // layer later on.
      token.setDetails(user.getName());

      // Save the authentication token.
      SecurityContextHolder.getContext().setAuthentication(token);

      LOGGER.debug(String.format("Profile information set for principal [%s].", authentication.getPrincipal()));

      return token;
    }
    catch (final Throwable t)
    {
      throw new AuthenticationServiceException(String.format("The email address [%s] could not be authenticated.", authentication.getName()), t);
    }
  }

  /**
   * Indicates that authentication using an email address and a password is
   * supported.
   */
  @Override
  public boolean supports(final Class<?> authentication)
  {
    return authentication != null && authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
