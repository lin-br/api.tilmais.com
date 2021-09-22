package com.tilmais.api.fake.factory;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.valueobjects.Name;

public class FakeNameFactory {

  private static final Faker FAKER = new Faker();

  public static Name makeValidFakeName() {
    return new Name(FAKER.name().fullName());
  }
}
