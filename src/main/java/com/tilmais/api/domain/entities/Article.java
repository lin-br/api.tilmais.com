package com.tilmais.api.domain.entities;

import com.tilmais.api.domain.entities.valueobjects.Comment;
import com.tilmais.api.domain.entities.valueobjects.articles.Body;
import com.tilmais.api.domain.entities.valueobjects.articles.Title;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Article {

  @NotNull(message = "The article need the created date and it's can't be null.")
  @PastOrPresent
  private final LocalDateTime created;

  @NotNull(message = "Address access of the article can't be null.")
  @NotBlank(message = "Address access of the article can't be empty.")
  public final String code;

  @NotNull(message = "The article title can't be null.")
  @Valid
  public final Title title;

  @NotNull(message = "The article body can't be null.")
  @Valid
  public final Body body;
  public final List<@NotNull(message = "If an article has a comment, it can't be null.") @Valid Comment> comments;

  public Article(final String code, final Title title, final Body body) {
    this.code = code;
    this.title = title;
    this.body = body;
    this.created = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("BET")));
    this.comments = new ArrayList<>();
  }

  public Article(final String code, final Title title, final Body body, final LocalDateTime created) {
    this.code = code;
    this.title = title;
    this.body = body;
    this.created = created;
    this.comments = new ArrayList<>();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Article article = (Article) o;
    return this.code.equals(article.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.code);
  }

  public String getCode() {
    return code;
  }

  public Title getTitle() {
    return title;
  }

  public Body getBody() {
    return body;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public LocalDateTime getCreated() {
    return created;
  }
}
