package com.tilmais.api.domain.entities.valueobjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Password {

  @NotNull(message = "The password can't be null.")
  @NotBlank(message = "The password can't be empty.")
  @Size(message = "The password must be between 8 and 30 characters in length.", min = 8, max = 30)
  public final String text;

  public Password(final String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
