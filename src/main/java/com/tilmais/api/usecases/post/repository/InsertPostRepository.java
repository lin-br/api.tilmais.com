package com.tilmais.api.usecases.post.repository;

import com.tilmais.api.domain.entities.Post;
import java.util.Optional;

public interface InsertPostRepository {

  public Optional<Post> insertPost(final Post post);
}
