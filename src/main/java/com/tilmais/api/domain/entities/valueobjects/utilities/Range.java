package com.tilmais.api.domain.entities.valueobjects.utilities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Range {

  @NotNull(message = "If a range is declared the field limit can't be null.")
  @Min(value = 1)
  @Max(value = 100)
  private final Integer limit;

  @NotNull(message = "If a offset is declared it's can't be null.")
  @Min(value = 1)
  @Max(value = 100)
  private final Integer offset;

  public Range(Integer limit) {
    this.limit = limit;
    this.offset = 0;
  }

  public Range(Integer limit, Integer offset) {
    this.limit = limit;
    this.offset = offset;
  }
}
