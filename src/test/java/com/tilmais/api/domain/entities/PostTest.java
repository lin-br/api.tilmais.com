package com.tilmais.api.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;

import com.tilmais.api.fake.factory.FakePostFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

  @Test
  @DisplayName("Should is same post when the other post has the same code.")
  void shouldIsSamePost() {
    var post = FakePostFactory.makeValidFakePost("code");
    var postTwo = FakePostFactory.makeValidFakePost("code");

    assertThat(post).isEqualTo(postTwo);
  }

  @Test
  @DisplayName("Should is not same post when the other post don't has the same code.")
  void shouldIsNotSamePost() {
    var post = FakePostFactory.makeValidFakePost("code");
    var postTwo = FakePostFactory.makeValidFakePost("other code");

    assertThat(post).isNotEqualTo(postTwo);
  }

  @Test
  @DisplayName("Should is not same post when the other object is not a post.")
  void shouldIsNotSamePostWhenOtherObjectIsNotPost() {
    var post = FakePostFactory.makeValidFakePost("code");

    assertThat(post).isNotEqualTo("postTwo");
  }

  @Test
  @DisplayName("Should is not same post when the other object is null.")
  void shouldIsNotSamePostWhenOtherObjectIsNull() {
    var post = FakePostFactory.makeValidFakePost("code");

    assertThat(post).isNotEqualTo(null);
  }

  @Test
  void shouldGetNotNullHashCode() {
    var post = FakePostFactory.makeValidFakePost("1");
    assertThat(post.hashCode()).isNotNull();
  }

  @Test
  void shouldNotEqualMyBirthdayWhenMutationTestDoNegateConditional() {
    var post = Post.newBuilder()
        .setCreated(null)
        .build();
    assertThat(post.getCreated())
        .isNotNull()
        .isNotEqualTo(LocalDateTime.of(LocalDate.of(1993, 7, 12), LocalTime.MIDNIGHT));
  }
}
