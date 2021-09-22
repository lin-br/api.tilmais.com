package com.tilmais.api.usecases.post;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.exceptions.RangeViolationException;
import com.tilmais.api.domain.entities.exceptions.UserViolationException;
import com.tilmais.api.domain.entities.valueobjects.utilities.Range;
import com.tilmais.api.fake.factory.FakePostFactory;
import com.tilmais.api.fake.factory.FakeRangeFactory;
import com.tilmais.api.fake.factory.FakeUserFactory;
import com.tilmais.api.usecases.post.repository.FindPostRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewPostUseCaseTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private ViewPostUseCase useCase;
  private FindPostRepository repositoryMock;

  @BeforeEach
  void setUp() {
    this.repositoryMock = mock(FindPostRepository.class);
    this.useCase = new ViewPostUseCase(this.repositoryMock, this.validator);
  }

  @Test
  @DisplayName("Should get posts by a range.")
  void shouldGetPostsByRange() {
    var range = FakeRangeFactory.makeValidFakeRange();
    var post = FakePostFactory.makeValidFakePost();
    when(repositoryMock.getPostByRange(any(Range.class)))
        .thenReturn(Collections.singletonList(post));

    var posts = this.useCase.getPostByRange(range);

    assertThat(posts)
        .isNotEmpty()
        .containsExactly(post);
    verify(this.repositoryMock, times(1)).getPostByRange(any(Range.class));
  }

  @Test
  @DisplayName("Should get posts by a range if It has invalid numbers on offset field.")
  void shouldDontGetPostsByRange() {
    var range = FakeRangeFactory.makeInvalidFakeRange();

    assertThatThrownBy(() -> this.useCase.getPostByRange(range))
        .isInstanceOf(RangeViolationException.class)
        .hasMessageContaining("must be between 1 and 100.");
    verify(this.repositoryMock, never()).getPostByRange(any());
  }

  @Test
  @DisplayName("Should get posts from an user with a range.")
  void shouldGetPostsFromUser() {
    var range = FakeRangeFactory.makeValidFakeRange();
    var post = FakePostFactory.makeValidFakePost();
    var user = FakeUserFactory.makeValidFakeUser();
    when(repositoryMock.getPostFromUserWithRange(any(User.class), any(Range.class)))
        .thenReturn(Collections.singletonList(post));

    var posts = this.useCase.getPostFromUser(user, range);

    assertThat(posts)
        .isNotEmpty()
        .containsExactly(post);

    verify(this.repositoryMock, times(1))
        .getPostFromUserWithRange(any(User.class), any(Range.class));
  }

  @Test
  @DisplayName("Don't should get posts from an user with a range, when the user is invalid.")
  void shouldDontGetPostsFromUserIfUserIsInvalid() {
    var range = FakeRangeFactory.makeValidFakeRange();
    var user = FakeUserFactory.makeInvalidFakeUserWithPasswordNull();

    assertThatThrownBy(() -> this.useCase.getPostFromUser(user, range))
        .isInstanceOf(UserViolationException.class)
        .hasMessageContaining("password: The password can't be null.");

    verify(this.repositoryMock, never()).getPostFromUserWithRange(any(), any());
  }

  @Test
  @DisplayName("Don't should get posts from an user with a range, when the range is invalid.")
  void shouldDontGetPostsFromUserIfRangeIsInvalid() {
    var range = FakeRangeFactory.makeInvalidFakeRange();
    var user = FakeUserFactory.makeValidFakeUser();

    assertThatThrownBy(() -> this.useCase.getPostFromUser(user, range))
        .isInstanceOf(RangeViolationException.class)
        .hasMessageContaining("must be between 1 and 100.");

    verify(this.repositoryMock, never()).getPostFromUserWithRange(any(), any());
  }
}