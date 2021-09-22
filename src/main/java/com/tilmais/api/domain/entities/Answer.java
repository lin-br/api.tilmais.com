package com.tilmais.api.domain.entities;

import static java.util.Objects.isNull;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Interaction;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.tools.Time;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Answer extends Interaction {

  @NotNull(message = "An answer need a parent and it can't be null.")
  @Valid
  private final Comment parent;

  private Answer(final EmailAddress emailAddress,
      final Name name,
      final LocalDateTime created,
      final String text,
      final Comment parent,
      final Integer number) {
    super(emailAddress, name, created, text, number);
    this.parent = parent;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Comment getParent() {
    return parent;
  }

  public static class Builder {

    private EmailAddress emailAddress;
    private Name name;
    private LocalDateTime created;
    private String text;
    private Comment parent;
    private Integer number;

    public Builder setCreated(LocalDateTime created) {
      this.created = created;
      return this;
    }

    public Builder setEmailAddress(EmailAddress emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    public Builder setName(Name name) {
      this.name = name;
      return this;
    }

    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    public Builder setNumber(Integer number) {
      this.number = number;
      return this;
    }

    public Builder setParent(Comment parent) {
      this.parent = parent;
      return this;
    }

    public Answer build() {
      return new Answer(this.emailAddress, this.name, getCreatedField(), this.text, this.parent, this.number);
    }

    private LocalDateTime getCreatedField() {
      return isNull(this.created) ? Time.getTimeNowFromSaoPaulo() : this.created;
    }
  }
}
