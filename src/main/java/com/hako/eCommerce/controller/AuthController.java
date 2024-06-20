package com.hako.eCommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hako.eCommerce.core.results.exceptions.UserException;
import com.hako.eCommerce.config.JwtProvider;
import com.hako.eCommerce.config.PasswordEncoder;
import com.hako.eCommerce.entities.User;
import com.hako.eCommerce.repository.abstarcts.UserRepository;
import com.hako.eCommerce.business.concretes.UserManager;

import com.hako.eCommerce.dto.User.requests.LoginRequest;
import com.hako.eCommerce.dto.User.responses.AuthResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

  private UserRepository userRepository;
  private JwtProvider jwtProvider;
  private PasswordEncoder passwordEncoder;
  private UserManager userManager;

  public AuthController(UserRepository userRepository,JwtProvider jwtProvider, PasswordEncoder passwordEncoder, UserManager userManager) {
    this.userRepository = userRepository;
    this.jwtProvider = jwtProvider;
    this.passwordEncoder = passwordEncoder;
    this.userManager = userManager;
  }

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
    String email = user.getEmail();
    String password = user.getPassword();
    String firstName = user.getFirstName();
    String lastName = user.getLastName();

    User isEmailExist = this.userRepository.findByEmail(email);
    if(isEmailExist != null) {
      throw new UserException("Email already exist");
    }

    User createdUser = new User();

    createdUser.setEmail(email);
    createdUser.setPassword(passwordEncoder.encode(password));
    createdUser.setFirstName(firstName);
    createdUser.setLastName(lastName);

    User savedUser = this.userRepository.save(createdUser);
    Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(token);
    authResponse.setMessage("Signup successful");
    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
  }

  @PostMapping("/signin")
  public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {
    String username = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Authentication authentication = authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(token);
    authResponse.setMessage("Signin successful");
    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
  }

  private Authentication authenticate(String userName, String password) {
    UserDetails userDetails = userManager.loadUserByUsername(userName);
    if(userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid username or password");
    }

    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}
