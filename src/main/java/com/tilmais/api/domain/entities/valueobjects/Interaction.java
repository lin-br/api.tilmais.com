package com.tilmais.api.domain.entities.valueobjects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public abstract class Interaction {

  @NotNull(message = "The e-mail in an interaction can't be null.")
  @Valid
  protected final EmailAddress emailAddress;

  @NotNull(message = "The name in an interaction can't be null.")
  @Valid
  protected final Name name;

  @NotNull(message = "An interaction need the created date and it can't be null.")
  @PastOrPresent
  protected final LocalDateTime created;

  @NotNull(message = "An interaction need a text and it can't be null.")
  @NotBlank(message = "The text can't be blank.")
  @Size(min = 1, max = 1000)
  protected final String text;

  @NotNull(message = "The comment number can't be null.")
  @Min(value = 1, message = "The number can't be negative")
  protected final Integer number;

  protected Interaction(final EmailAddress emailAddress,
      final Name name,
      final LocalDateTime created,
      final String text,
      final Integer number) {
    this.emailAddress = emailAddress;
    this.name = name;
    this.created = created;
    this.text = text;
    this.number = number;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public Name getName() {
    return name;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public String getText() {
    return text;
  }

  public Integer getNumber() {
    return number;
  }
}
