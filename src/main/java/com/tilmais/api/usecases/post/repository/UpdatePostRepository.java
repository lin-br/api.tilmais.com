package com.tilmais.api.usecases.post.repository;

import com.tilmais.api.domain.entities.Post;
import java.util.Optional;

public interface UpdatePostRepository {

  Optional<Post> updatePost(Post post);
}
