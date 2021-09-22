package com.tilmais.api.domain.entities;

import static java.util.Objects.isNull;

import com.tilmais.api.domain.entities.valueobjects.post.Body;
import com.tilmais.api.domain.entities.valueobjects.post.Title;
import com.tilmais.api.domain.tools.Time;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Collections;
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

  private Post(final LocalDateTime created,
      final String code,
      final Title title,
      final Body body,
      final List<Comment> comments) {
    this.created = created;
    this.code = code;
    this.title = title;
    this.body = body;
    this.comments = comments;
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

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {
    private LocalDateTime created;
    private String code;
    private Title title;
    private Body body;
    private List<Comment> comments;

    public Builder setCreated(LocalDateTime created) {
      this.created = created;
      return this;
    }

    public Builder setCode(String code) {
      this.code = code;
      return this;
    }

    public Builder setTitle(Title title) {
      this.title = title;
      return this;
    }

    public Builder setBody(Body body) {
      this.body = body;
      return this;
    }

    public Builder setComments(List<Comment> comments) {
      this.comments = comments;
      return this;
    }

    public Post build() {
      return new Post(getCreatedField(), this.code, this.title, this.body, getComments());
    }

    private LocalDateTime getCreatedField() {
      return isNull(this.created) ? Time.getTimeNowFromSaoPaulo() : this.created;
    }

    private List<Comment> getComments() {
      return isNull(this.comments) ? Collections.emptyList() : this.comments;
    }
  }
}
