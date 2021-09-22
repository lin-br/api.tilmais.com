package com.tilmais.api.domain.entities.valueobjects.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class Title {

  @NotNull(message = "The title can't be null.")
  @NotBlank(message = "The title can't be empty.")
  @Size(message = "The title must be between 20 and 150 characters in length.", min = 20, max = 150)
  private final String text;

  public Title(final String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Title title = (Title) o;
    return this.text.equals(title.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  public String getText() {
    return text;
  }

  public boolean isNotEquals(Title title) {
    return !this.equals(title);
  }
}
