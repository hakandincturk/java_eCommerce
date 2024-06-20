package com.hako.eCommerce.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.rules.UserBusinessRules;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.UserRepository;

@Service
public class UserManager implements UserDetailsService {

  private UserRepository userRepository;
  private UserBusinessRules userBusinessRules;

  public UserManager(UserRepository userRepository, UserBusinessRules userBusinessRules) {
    this.userRepository = userRepository;
    this.userBusinessRules = userBusinessRules;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userBusinessRules.checkUserEmail(username);
    List<GrantedAuthority> authorities = new ArrayList<>() {};
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
  }
  
}
