package com.uni.c02015.services;

import com.uni.c02015.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.uni.c02015.SpringMvc.*;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepo;

  /**
   * Load by username.
   * @param login String
   * @return UserDetails
   * @throws UsernameNotFoundException Throws on error
   */
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

    com.uni.c02015.domain.User user = userRepo.findByLogin(login);

    if (user == null) {

      throw new UsernameNotFoundException("user not found");

    }

    return new User(user.getLogin(), user.getPassword(),
        user.getConfirmed(), true, true, !user.isSuspended(),
        getAuthorities(user.getRole().getId()));
  }

  /**
   * Get authorities.
   * @param role Integer
   * @return GrantedAuthority Collection
   */
  private Collection<? extends GrantedAuthority> getAuthorities(Integer role) {

    return getGrantedAuthorities(getRoles(role));
  }

  /**
   * Get roles.
   * @param role Integer
   * @return String List
   */
  private List<String> getRoles(Integer role) {

    List<String> roles = new ArrayList<String>();

    if (role == ROLE_ADMINISTRATOR_ID) {

      roles.add("ROLE_" + ROLE_ADMINISTRATOR);

    } else if (role == ROLE_LANDLORD_ID) {

      roles.add("ROLE_" + ROLE_LANDLORD);

    } else if (role == ROLE_SEARCHER_ID) {

      roles.add("ROLE_" + ROLE_SEARCHER);
    }

    return roles;
  }

  /**
   * Get granted authorities.
   * @param roles String list
   * @return GrantedAuthority List
   */
  private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {

    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    for (String role : roles) {

      authorities.add(new SimpleGrantedAuthority(role));
    }

    return authorities;
  }
}