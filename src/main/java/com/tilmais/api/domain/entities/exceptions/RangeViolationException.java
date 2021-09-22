package com.tilmais.api.domain.entities.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;

public class RangeViolationException extends ConstraintViolationException {

  public RangeViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
    super(constraintViolations);
  }
}
