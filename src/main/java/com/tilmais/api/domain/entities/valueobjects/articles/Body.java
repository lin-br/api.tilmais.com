package com.tilmais.api.domain.entities.valueobjects.articles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Body {

  @NotNull(message = "The body can't be null.")
  @NotBlank(message = "The body can't be empty.")
  private final String text;

  public Body(final String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
