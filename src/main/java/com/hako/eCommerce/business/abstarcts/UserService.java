package com.hako.eCommerce.business.abstarcts;

import com.hako.eCommerce.core.results.exceptions.UserException;
import com.hako.eCommerce.entities.User;

public interface UserService {
  public User findUserById(Long id) throws UserException;
  public User findUserProfileByJwt(String jwt) throws UserException;
}
