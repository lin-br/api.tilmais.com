package com.tilmais.api.domain.entities.valueobjects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class Viewer {

  @NotNull
  @Valid
  protected final EmailAddress emailAddress;

  @NotNull
  @Valid
  protected final Name name;

  public Viewer(EmailAddress emailAddress, Name name) {
    this.emailAddress = emailAddress;
    this.name = name;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public Name getName() {
    return name;
  }
}
