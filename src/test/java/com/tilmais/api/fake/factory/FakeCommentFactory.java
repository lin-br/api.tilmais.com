package com.tilmais.api.fake.factory;

import static com.tilmais.api.fake.factory.FakeEmailAddressFactory.makeValidFakeEmailAddress;
import static com.tilmais.api.fake.factory.FakeNameFactory.makeValidFakeName;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.Comment;
import com.tilmais.api.domain.tools.Time;

public class FakeCommentFactory {

  private static final Faker FAKER = new Faker();

  public static Comment makeValidFakeComment() {
    return Comment.newBuilder()
        .setNumber(FAKER.number().randomDigitNotZero())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setName(makeValidFakeName())
        .setText(FAKER.lorem().paragraph())
        .build();
  }

  public static Comment makeFullValidFakeComment() {
    var comment = Comment.newBuilder()
        .setNumber(FAKER.number().randomDigitNotZero())
        .setEmailAddress(makeValidFakeEmailAddress())
        .setName(makeValidFakeName())
        .setText(FAKER.lorem().paragraph())
        .setCreated(Time.getTimeNowFromSaoPaulo())
        .build();

    var answer = FakeAnswerFactory.makeValidFakeAnswer(comment);

    comment.addAnswer(answer);
    return comment;
  }

  public static Comment makeInvalidFakeComment() {
    return Comment.newBuilder()
        .setNumber(null)
        .setEmailAddress(makeValidFakeEmailAddress())
        .setName(makeValidFakeName())
        .setText(FAKER.lorem().paragraph())
        .build();
  }
}
