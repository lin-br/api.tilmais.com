package com.tilmais.api.fake.factory;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.valueobjects.post.Body;
import com.tilmais.api.domain.entities.valueobjects.post.Title;

public class FakePostFactory {

  private static final Faker FAKER = new Faker();

  public static Post makeValidFakePost() {
    return new Post(FAKER.code().gtin8(), makeValidFakeTitle(), makeValidFakeBody());
  }

  private static Body makeValidFakeBody() {
    return new Body(FAKER.lorem().paragraph());
  }

  private static Title makeValidFakeTitle() {
    return new Title(FAKER.lorem().characters(20, 150));
  }

  public static Post makeInvalidFakePost() {
    return new Post(null, makeValidFakeTitle(), makeValidFakeBody());
  }
}
