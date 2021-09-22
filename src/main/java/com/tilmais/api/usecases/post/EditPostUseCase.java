package com.tilmais.api.usecases.post;

import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.exceptions.PostViolationsException;
import com.tilmais.api.usecases.post.repository.UpdatePostRepository;
import jakarta.validation.Validator;
import java.util.Optional;

public class EditPostUseCase {

  private final UpdatePostRepository repository;
  private final Validator validator;

  public EditPostUseCase(final UpdatePostRepository repository, final Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  public Optional<Post> editPost(final Post post) throws PostViolationsException {
    var setOldPostViolations = this.validator.validate(post);

    if (!setOldPostViolations.isEmpty()) {
      throw new PostViolationsException(setOldPostViolations);
    }

    return this.repository.updatePost(post);
  }
}
