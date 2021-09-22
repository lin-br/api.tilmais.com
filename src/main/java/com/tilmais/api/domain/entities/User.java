package com.tilmais.api.domain.entities;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
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

  private final List<@NotNull(message = "A user does not have an post list with the null value.") @Valid Post> posts =
      new ArrayList<>();

  public User(final Name name, final EmailAddress emailAddress, final Password password) {
    this.name = name;
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public void addPost(Post post) {
    this.posts.add(post);
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
}
