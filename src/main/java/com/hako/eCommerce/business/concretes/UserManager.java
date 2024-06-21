package com.hako.eCommerce.business.concretes;

import org.springframework.stereotype.Service;

import com.hako.eCommerce.business.abstarcts.UserService;
import com.hako.eCommerce.business.rules.UserBusinessRules;
import com.hako.eCommerce.config.JwtProvider;
import com.hako.eCommerce.core.results.exceptions.UserException;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.UserRepository;

@Service
public class UserManager implements UserService {

  private UserRepository userRepository;
  private JwtProvider jwtProvider;
  private UserBusinessRules userBusinessRules;

  public UserManager(UserRepository userRepository, JwtProvider jwtProvider, UserBusinessRules userBusinessRules) {
    this.userRepository = userRepository;
    this.jwtProvider = jwtProvider;
    this.userBusinessRules = userBusinessRules;
  }

  @Override
  public User findUserById(Long id) throws UserException {
    this.userBusinessRules.checkUserById(id);
    User user = this.userRepository.findById(id).orElse(null);
    return user;
  }

  @Override
  public User findUserProfileByJwt(String jwt) throws UserException {
    String email = this.jwtProvider.getEmailFromToken(jwt);
    this.userBusinessRules.checkUserEmail(email);
    
    User user = this.userRepository.findByEmail(email);
    return user;
  }
  
}
