package com.hako.eCommerce.business.rules;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.UserRepository;

@Service
public class UserBusinessRules {
  private UserRepository userRepository;

  public UserBusinessRules(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User checkUserEmail(String email) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
    return user;
  }
}
