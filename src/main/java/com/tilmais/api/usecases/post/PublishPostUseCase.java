package com.tilmais.api.usecases.post;

import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.exceptions.PostViolationsException;
import com.tilmais.api.usecases.post.repository.InsertPostRepository;
import jakarta.validation.Validator;
import java.util.Optional;

public class PublishPostUseCase {

  private final InsertPostRepository repository;
  private final Validator validator;

  public PublishPostUseCase(final InsertPostRepository repository, final Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  public Optional<Post> publishPost(final Post post) throws PostViolationsException {
    var violations = this.validator.validate(post);

    if (!violations.isEmpty()) {
      throw new PostViolationsException(violations);
    }

    return this.repository.insertPost(post);
  }
}
