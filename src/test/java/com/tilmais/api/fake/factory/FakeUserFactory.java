package com.tilmais.api.fake.factory;

import static com.tilmais.api.fake.factory.FakeEmailAddressFactory.makeValidFakeEmailAddress;
import static com.tilmais.api.fake.factory.FakeNameFactory.makeValidFakeName;
import static com.tilmais.api.fake.factory.FakePostFactory.makeValidFakePost;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
import java.util.Collections;

public class FakeUserFactory {

  private static final Faker FAKER = new Faker();

  public static User makeValidFakeUser() {
    var password = new Password(FAKER.internet().password());
    return User.newBuilder()
        .setName(makeValidFakeName())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setPassword(password)
        .setPosts(Collections.singletonList(makeValidFakePost()))
        .build();
  }

  public static User makeInvalidFakeUserWithPasswordNull() {
    return User.newBuilder()
        .setName(makeValidFakeName())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setPassword(null)
        .build();
  }

  public static User makeValidFakeUser(Name name) {
    var password = new Password(FAKER.internet().password());
    return User.newBuilder()
        .setName(name)
        .setEmailAddress(makeValidFakeEmailAddress())
        .setPassword(password)
        .setPosts(Collections.singletonList(makeValidFakePost()))
        .build();
  }
}
