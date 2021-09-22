package com.tilmais.api.usecases.user.repository;

import com.tilmais.api.domain.entities.User;
import java.util.Optional;

public interface UpdateUserRepository {

  public Optional<User> updateUserName(final String email, final String newName);
}
