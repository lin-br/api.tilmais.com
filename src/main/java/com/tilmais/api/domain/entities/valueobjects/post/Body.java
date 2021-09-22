package com.tilmais.api.domain.entities.valueobjects.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class Body {

  @NotNull(message = "The body can't be null.")
  @NotBlank(message = "The body can't be empty.")
  private final String text;

  public Body(final String text) {
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
    Body body = (Body) o;
    return this.text.equals(body.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  public String getText() {
    return text;
  }

  public boolean isNotEquals(Body body) {
    return !this.equals(body);
  }
}
