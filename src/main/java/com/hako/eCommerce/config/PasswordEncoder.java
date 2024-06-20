package com.hako.eCommerce.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder extends BCryptPasswordEncoder {
  public PasswordEncoder() {
    super();
  }
  
  public String encodePassword(String password) {
    return super.encode(password);
  }
  
  public boolean matchesPassword(String password, String encodedPassword) {
    return super.matches(password, encodedPassword);
  }
}
