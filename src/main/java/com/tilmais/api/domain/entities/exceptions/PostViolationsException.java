package com.tilmais.api.domain.entities.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;

public class PostViolationsException extends ConstraintViolationException {

  public PostViolationsException(Set<? extends ConstraintViolation<?>> constraintViolations) {
    super(constraintViolations);
  }
}
