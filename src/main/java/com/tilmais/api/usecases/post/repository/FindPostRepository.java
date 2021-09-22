package com.tilmais.api.usecases.post.repository;

import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.valueobjects.utilities.Range;
import java.util.List;

public interface FindPostRepository {

  public List<Post> getPostByRange(final Range range);

  public List<Post> getPostFromUserWithRange(final User user, final Range range);
}
