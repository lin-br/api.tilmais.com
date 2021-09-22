package com.tilmais.api.fake.factory;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.valueobjects.utilities.Range;

public class FakeRangeFactory {

  private static final Faker FAKER = new Faker();

  public static Range makeValidFakeRange() {
    return new Range(FAKER.number().randomDigitNotZero());
  }

  public static Range makeInvalidFakeRange() {
    return new Range(FAKER.number().randomDigitNotZero(), -1);
  }
}
