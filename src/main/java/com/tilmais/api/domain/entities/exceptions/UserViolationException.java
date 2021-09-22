package com.tilmais.api.domain.entities.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;

public class UserViolationException extends ConstraintViolationException {

  public UserViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
    super(constraintViolations);
  }
}
