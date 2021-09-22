package com.tilmais.api.domain.entities.valueobjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class Name {

  @NotNull(message = "The name can't be null.")
  @NotBlank(message = "The name can't be empty.")
  @Size(message = "The name must be between 1 and 60 characters in length.", min = 1, max = 60)
  private final String fullName;

  public Name(final String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Name name = (Name) o;
    return this.fullName.equals(name.fullName);
  }

  public boolean isNotEquals(Name name) {
    return !this.equals(name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.fullName);
  }
}
