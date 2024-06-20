package com.hako.eCommerce.dto.User.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;
}
