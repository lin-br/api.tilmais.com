package com.tilmais.api.domain.entities.valueobjects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Comment {

  @NotNull(message = "The comment need the created date and it's can't be null.")
  @PastOrPresent
  private final LocalDateTime created;

  @NotNull(message = "The comment owner can't be null.")
  @Valid
  private final Viewer owner;

  @NotNull
  @NotBlank
  @Size(min = 1, max = 1000)
  private final String text;

  private final List<@NotNull(message = "If a comment has an answer, it can't be null.") @Valid Comment> answers;

  public Comment(final Viewer owner, final String text) {
    this.owner = owner;
    this.text = text;
    this.created = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("BET")));
    this.answers = new ArrayList<>();
  }

  public Comment(final Viewer owner, final String text, final LocalDateTime created) {
    this.owner = owner;
    this.text = text;
    this.created = created;
    this.answers = new ArrayList<>();
  }

  public void addAnswer(Comment answer) {
    this.answers.add(answer);
  }

  public Viewer getOwner() {
    return owner;
  }

  public String getText() {
    return text;
  }

  public List<Comment> getAnswers() {
    return answers;
  }

  public LocalDateTime getCreated() {
    return created;
  }
}
