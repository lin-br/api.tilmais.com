package com.tilmais.api.domain.entities.valueobjects.articles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Title {

  @NotNull(message = "The title can't be null.")
  @NotBlank(message = "The title can't be empty.")
  @Size(message = "The title must be between 20 and 150 characters in length.", min = 20, max = 150)
  private final String text;

  public Title(final String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
