package com.tilmais.api.domain.entities;

import com.tilmais.api.domain.entities.valueobjects.post.Body;
import com.tilmais.api.domain.entities.valueobjects.post.Title;
import com.tilmais.api.domain.tools.Time;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

  @NotNull(message = "The post need the created date and it's can't be null.")
  @PastOrPresent
  private final LocalDateTime created;

  @NotNull(message = "Address access of the post can't be null.")
  @NotBlank(message = "Address access of the post can't be empty.")
  private final String code;

  @NotNull(message = "The post title can't be null.")
  @Valid
  private final Title title;

  @NotNull(message = "The post body can't be null.")
  @Valid
  private final Body body;
  private final List<@NotNull(message = "If an post has a comment, it can't be null.") @Valid Comment> comments;

  public Post(final String code, final Title title, final Body body) {
    this.code = code;
    this.title = title;
    this.body = body;
    this.created = Time.getTimeNowFromSaoPaulo();
    this.comments = new ArrayList<>();
  }

  public Post(final String code, final Title title, final Body body, final LocalDateTime created) {
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
    Post post = (Post) o;
    return this.code.equals(post.code);
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
