package com.uni.c02015;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(
      final HttpServletRequest request, final HttpServletResponse response,
      final AuthenticationException exception) throws IOException, ServletException {
    setDefaultFailureUrl("/invalid-login");

    String errorMessage = "It seems as though you have entered an invalid username or password.";

    if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
      setDefaultFailureUrl("/confirm/email");
      errorMessage = "It seems as though your user accout has not been activated";
    } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
      errorMessage = "It seems as though your user accout has expired";
    } else if (exception.getMessage().equalsIgnoreCase("blocked")) {
      errorMessage = "It seems as though your user accout is blocked";
    }

    super.onAuthenticationFailure(request, response, exception);
    request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
  }
}