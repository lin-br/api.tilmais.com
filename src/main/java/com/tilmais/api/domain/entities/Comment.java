package com.tilmais.api.domain.entities;

import static java.util.Objects.isNull;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Interaction;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.tools.Time;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Comment extends Interaction {

  private final List<@NotNull(message = "If a comment has an answer, it can't be null.") @Valid Answer> answers;

  private Comment(final EmailAddress emailAddress,
      final Name name,
      final LocalDateTime created,
      final String text,
      final Integer number,
      final List<Answer> answers) {
    super(emailAddress, name, created, text, number);
    this.answers = answers;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public void addAnswer(final Answer answer) {
    this.answers.add(answer);
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public static class Builder {

    private EmailAddress emailAddress;
    private Name name;
    private LocalDateTime created;
    private String text;
    private Integer number;
    private List<Answer> answers;

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

    public Builder setAnswers(List<Answer> answers) {
      this.answers = answers;
      return this;
    }

    public Comment build() {
      return new Comment(this.emailAddress, this.name, getCreatedField(), this.text, this.number, getAnswers());
    }

    private List<Answer> getAnswers() {
      return isNull(this.answers) ? Collections.emptyList() : this.answers;
    }

    private LocalDateTime getCreatedField() {
      return isNull(this.created) ? Time.getTimeNowFromSaoPaulo() : this.created;
    }
  }
}
