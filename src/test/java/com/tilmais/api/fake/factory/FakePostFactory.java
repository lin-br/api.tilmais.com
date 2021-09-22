package com.tilmais.api.fake.factory;

import static com.tilmais.api.fake.factory.FakeCommentFactory.makeValidFakeComment;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.valueobjects.post.Body;
import com.tilmais.api.domain.entities.valueobjects.post.Title;
import com.tilmais.api.domain.tools.Time;
import java.util.Collections;

public class FakePostFactory {

  private static final Faker FAKER = new Faker();

  public static Post makeValidFakePost() {
    return Post.newBuilder()
        .setCode(FAKER.code().gtin8())
        .setTitle(makeValidFakeTitle())
        .setBody(makeValidFakeBody())
        .setComments(Collections.singletonList(makeValidFakeComment()))
        .setCreated(Time.getTimeNowFromSaoPaulo())
        .build();
  }

  public static Post makeValidFakePost(String code) {
    return Post.newBuilder()
        .setCode(code)
        .setTitle(makeValidFakeTitle())
        .setBody(makeValidFakeBody())
        .setComments(Collections.singletonList(makeValidFakeComment()))
        .setCreated(Time.getTimeNowFromSaoPaulo())
        .build();
  }

  private static Body makeValidFakeBody() {
    return new Body(FAKER.lorem().paragraph());
  }

  private static Title makeValidFakeTitle() {
    return new Title(FAKER.lorem().characters(20, 150));
  }

  public static Post makeInvalidFakePost() {
    return Post.newBuilder()
        .setCode(null)
        .setTitle(makeValidFakeTitle())
        .setBody(makeValidFakeBody())
        .build();
  }
}
