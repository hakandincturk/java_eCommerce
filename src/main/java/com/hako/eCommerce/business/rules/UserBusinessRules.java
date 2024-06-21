package com.hako.eCommerce.business.rules;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hako.eCommerce.core.results.exceptions.UserException;
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

  public void checkUserById(Long id) throws UserException {
    User user = this.userRepository.findById(id).orElse(null);
    if (user == null) {
      throw new UserException("User not found with id: " + id);
    }
  }
}
