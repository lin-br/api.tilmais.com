package com.tilmais.api.fake.factory;

import static com.tilmais.api.fake.factory.FakeCommentFactory.makeValidFakeComment;
import static com.tilmais.api.fake.factory.FakeEmailAddressFactory.makeValidFakeEmailAddress;
import static com.tilmais.api.fake.factory.FakeNameFactory.makeValidFakeName;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.Answer;
import com.tilmais.api.domain.entities.Comment;
import com.tilmais.api.domain.tools.Time;

public class FakeAnswerFactory {

  private static final Faker FAKER = new Faker();

  public static Answer makeValidFakeAnswer() {
    return Answer.newBuilder()
        .setParent(makeValidFakeComment())
        .setNumber(FAKER.number().randomDigitNotZero())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setName(makeValidFakeName())
        .setText(FAKER.lorem().paragraph())
        .setCreated(Time.getTimeNowFromSaoPaulo())
        .build();
  }

  public static Answer makeValidFakeAnswer(final Comment comment) {
    return Answer.newBuilder()
        .setParent(comment)
        .setNumber(FAKER.number().randomDigitNotZero())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setName(makeValidFakeName())
        .setText(FAKER.lorem().paragraph())
        .setCreated(Time.getTimeNowFromSaoPaulo())
        .build();
  }

  public static Answer makeInvalidFakeAnswer() {
    return Answer.newBuilder()
        .setParent(null)
        .setNumber(FAKER.number().randomDigitNotZero())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setName(makeValidFakeName())
        .setText(FAKER.lorem().paragraph())
        .setCreated(Time.getTimeNowFromSaoPaulo())
        .build();
  }
}
