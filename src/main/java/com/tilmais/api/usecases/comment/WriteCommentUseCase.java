package com.tilmais.api.usecases.comment;

import com.tilmais.api.domain.entities.Answer;
import com.tilmais.api.domain.entities.Comment;
import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.exceptions.AnswerViolationsException;
import com.tilmais.api.domain.entities.exceptions.CommentViolationsException;
import com.tilmais.api.domain.entities.exceptions.PostViolationsException;
import com.tilmais.api.usecases.comment.repository.CreateCommentRepository;
import jakarta.validation.Validator;
import java.util.Optional;

public class WriteCommentUseCase {

  private final CreateCommentRepository repository;
  private final Validator validator;

  public WriteCommentUseCase(CreateCommentRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  public Optional<Comment> writeCommentOnPost(final Post post, final Comment comment)
      throws PostViolationsException, CommentViolationsException {
    var postViolations = this.validator.validate(post);
    var commentViolations = this.validator.validate(comment);

    if (!postViolations.isEmpty()) {
      throw new PostViolationsException(postViolations);
    }

    if (!commentViolations.isEmpty()) {
      throw new CommentViolationsException(commentViolations);
    }

    return this.repository.createCommentOnPost(post, comment);
  }

  public Optional<Comment> answerComment(final Comment parent, final Answer answer)
      throws CommentViolationsException, AnswerViolationsException {
    var parentViolations = this.validator.validate(parent);
    var answerViolations = this.validator.validate(answer);

    if (!parentViolations.isEmpty()) {
      throw new CommentViolationsException(parentViolations);
    }

    if (!answerViolations.isEmpty()) {
      throw new AnswerViolationsException(answerViolations);
    }

    return this.repository.createAnswer(parent, answer);
  }
}
