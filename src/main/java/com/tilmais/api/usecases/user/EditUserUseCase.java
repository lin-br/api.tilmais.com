package com.tilmais.api.usecases.user;

import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.exceptions.UserViolationException;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.usecases.user.repository.UpdateUserRepository;
import jakarta.validation.Validator;
import java.util.Optional;

public class EditUserUseCase {

  private final UpdateUserRepository updateUserRepository;
  private final Validator validator;

  public EditUserUseCase(UpdateUserRepository updateUserRepository, Validator validator) {
    this.updateUserRepository = updateUserRepository;
    this.validator = validator;
  }

  public Optional<User> changeUserName(final User user, final Name newName) throws UserViolationException {
    var setUserConstraintViolations = validator.validate(user);
    var setNameConstraintViolations = validator.validate(newName);

    if (!setUserConstraintViolations.isEmpty()) {
      throw new UserViolationException(setUserConstraintViolations);
    }

    if (!setNameConstraintViolations.isEmpty()) {
      throw new UserViolationException(setNameConstraintViolations);
    }

    if (user.getName().isNotEquals(newName)) {
      return this.updateUserRepository.updateUserName(user.getEmailAddress().getAddress(), newName.getFullName());
    }

    return Optional.of(user);
  }
}
