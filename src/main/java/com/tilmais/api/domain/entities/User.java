package com.tilmais.api.domain.entities;

import static java.util.Objects.isNull;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {

  @NotNull(message = "The password can't be null.")
  @Valid
  private final Password password;

  @NotNull(message = "The e-mail can't be null.")
  @Valid
  private final EmailAddress emailAddress;

  @NotNull(message = "The name can't be null.")
  @Valid
  private final Name name;

  private final List<@NotNull(message = "A user does not have an post list with the null value.") @Valid Post> posts;

  private User(final Name name,
      final EmailAddress emailAddress,
      final Password password,
      final List<Post> posts) {
    this.name = name;
    this.emailAddress = emailAddress;
    this.password = password;
    this.posts = posts;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    if (this == object) {
      return true;
    }
    User user = (User) object;
    return this.emailAddress.getAddress().equals(user.emailAddress.getAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.emailAddress);
  }

  public Password getPassword() {
    return password;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
  }

  public Name getName() {
    return name;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {
    private Password password;
    private EmailAddress emailAddress;
    private Name name;
    private List<Post> posts;

    public Builder setPassword(Password password) {
      this.password = password;
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

    public Builder setPosts(List<Post> posts) {
      this.posts = posts;
      return this;
    }

    public User build() {
      return new User(this.name, this.emailAddress, this.password, makePosts());
    }

    private List<Post> makePosts() {
      return isNull(this.posts) ? Collections.emptyList() : this.posts;
    }
  }
}
