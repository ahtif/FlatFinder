package com.uni.c02015;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private CustomAuthenticationFailureHandler failureHandler;

  /**
   * Web security configuration.
   * @param http HttpSecurity
   * @throws Exception Throws on error
   */
  protected void configure(HttpSecurity http) throws Exception {
    
    http
      .authorizeRequests()
      .antMatchers("/user-logout",
          "/register",
          "/createAccount",
          "/searcher/registration",
          "/landlord/registration",
          "/addLandlord",
          "/addSearcher",
<<<<<<< HEAD
          "/confirm/**",
          "/invalid-login",
          "/forgot/**",
          "/recover/**").permitAll()
=======
          "/resources/**").permitAll()
      .antMatchers("/admin/**").hasAnyRole(SpringMvc.ROLE_ADMINISTRATOR)
>>>>>>> master
      .antMatchers("/searcher/**").hasAnyRole(SpringMvc.ROLE_SEARCHER)
      .antMatchers("/landlord/**").hasAnyRole(SpringMvc.ROLE_LANDLORD)
      .antMatchers("/property/add", "/property/addPost", 
        "/property/viewAll").hasRole(SpringMvc.ROLE_LANDLORD)
      .antMatchers("/messaging/**")
        .hasAnyRole(SpringMvc.ROLE_ADMINISTRATOR,
            SpringMvc.ROLE_SEARCHER,
            SpringMvc.ROLE_LANDLORD)
      .anyRequest().authenticated()
      .and()
    .formLogin()
      .loginPage("/")
      .loginProcessingUrl("/login")
      .defaultSuccessUrl("/success-login", true)
      .failureHandler(failureHandler)
      .permitAll()
      .and()
    .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/user-logout")
      .and()
    .requiresChannel()
      .anyRequest()
      .requiresSecure()
      .and()
    .exceptionHandling()
      .accessDeniedPage("/user-error");
  }

  /**
   * Global password encoding settings.
   * @param auth AuthenticationManagerBuilder
   * @throws Exception Throws on error
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    auth.userDetailsService(userDetailsService).passwordEncoder(pe);
  }
}