package com.hako.eCommerce.repository.abstarcts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hako.eCommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
  public User findByEmail(String email);
}
