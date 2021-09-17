package com.tilmais.api.domain.entities.valueobjects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmailAddress {

  @NotNull(message = "E-mail address is null, please verify and try again.")
  @NotBlank(message = "E-mail address is blank, please verify and try again.")
  @Email(message = "It is not a valid e-mail address: ${validatedValue}")
  private final String address;

  public EmailAddress(final String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }
}
