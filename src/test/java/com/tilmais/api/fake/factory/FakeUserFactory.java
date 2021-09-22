package com.tilmais.api.fake.factory;

import static com.tilmais.api.fake.factory.FakeEmailAddressFactory.makeValidFakeEmailAddress;
import static com.tilmais.api.fake.factory.FakeNameFactory.makeValidFakeName;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.valueobjects.Password;

public class FakeUserFactory {

  private static final Faker FAKER = new Faker();

  public static User makeValidFakeUser() {
    var password = new Password(FAKER.internet().password());
    return new User(makeValidFakeName(), makeValidFakeEmailAddress(), password);
  }

  public static User makeInvalidFakeUserWithPasswordNull() {
    return new User(makeValidFakeName(), makeValidFakeEmailAddress(), null);
  }
}
