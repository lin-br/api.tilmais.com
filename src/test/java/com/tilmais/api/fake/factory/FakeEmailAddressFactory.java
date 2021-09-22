package com.tilmais.api.fake.factory;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.valueobjects.EmailAddress;

public class FakeEmailAddressFactory {

  private static final Faker FAKER = new Faker();

  public static EmailAddress makeValidFakeEmailAddress() {
    return new EmailAddress(FAKER.internet().safeEmailAddress());
  }
}
