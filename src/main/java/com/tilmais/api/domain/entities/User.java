package com.tilmais.api.domain.entities;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
import com.tilmais.api.domain.entities.valueobjects.Viewer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Viewer {

  @NotNull
  @Valid
  private final Password password;

  private final List<@NotNull(message = "A user does not have an article list with the null value.") @Valid Article> articles =
      new ArrayList<>();

  public User(final Name name, final EmailAddress emailAddress, final Password password) {
    super(emailAddress, name);
    this.password = password;
  }

  public void addArticle(Article article) {
    this.articles.add(article);
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

  public List<Article> getArticles() {
    return articles;
  }
}