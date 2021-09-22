package com.tilmais.api.usecases.comment.repository;

import com.tilmais.api.domain.entities.Answer;
import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.Comment;
import java.util.Optional;

public interface CreateCommentRepository {

  public Optional<Comment> createCommentOnPost(final Post post, final Comment comment);

  public Optional<Comment> createAnswer(final Comment parent, final Answer answer);
}
