package com.tilmais.api.usecases.post;

import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.exceptions.RangeViolationException;
import com.tilmais.api.domain.entities.exceptions.UserViolationException;
import com.tilmais.api.domain.entities.valueobjects.utilities.Range;
import com.tilmais.api.usecases.post.repository.FindPostRepository;
import jakarta.validation.Validator;
import java.util.List;

public class ViewPostUseCase {

  private final FindPostRepository repository;
  private final Validator validator;

  public ViewPostUseCase(final FindPostRepository repository, final Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  public List<Post> getPostByRange(final Range range) throws RangeViolationException {
    var violations = this.validator.validate(range);

    if (!violations.isEmpty()) {
      throw new RangeViolationException(violations);
    }

    return this.repository.getPostByRange(range);
  }

  public List<Post> getPostFromUser(final User user, final Range range) throws UserViolationException, RangeViolationException {
    var userViolations = this.validator.validate(user);
    var rangeViolations = this.validator.validate(range);

    if (!userViolations.isEmpty()) {
      throw new UserViolationException(userViolations);
    }

    if (!rangeViolations.isEmpty()) {
      throw new RangeViolationException(rangeViolations);
    }

    return this.repository.getPostFromUserWithRange(user, range);
  }
}
