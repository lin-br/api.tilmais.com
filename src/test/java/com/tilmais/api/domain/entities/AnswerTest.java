package com.tilmais.api.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class AnswerTest {

  @Test
  void shouldNotEqualMyBirthdayWhenMutationTestDoNegateConditional() {
    var answer = Answer.newBuilder()
        .setCreated(null)
        .build();
    assertThat(answer.getCreated())
        .isNotNull()
        .isNotEqualTo(LocalDateTime.of(LocalDate.of(1993, 7, 12), LocalTime.MIDNIGHT));
  }
}
