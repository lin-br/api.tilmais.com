package com.tilmais.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.tilmais.api.fake.factory.FakePostFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

  @Test
  @DisplayName("Should is same post when the other post has the same code.")
  void shouldIsSamePost() {
    var post = FakePostFactory.makeValidFakePost("code");
    var postTwo = FakePostFactory.makeValidFakePost("code");

    assertEquals(post, postTwo);
  }

  @Test
  @DisplayName("Should is not same post when the other post don't has the same code.")
  void shouldIsNotSamePost() {
    var post = FakePostFactory.makeValidFakePost("code");
    var postTwo = FakePostFactory.makeValidFakePost("other code");

    assertNotEquals(post, postTwo);
  }
}
