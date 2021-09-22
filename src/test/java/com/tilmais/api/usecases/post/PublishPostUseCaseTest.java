package com.tilmais.api.usecases.post;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.exceptions.PostViolationsException;
import com.tilmais.api.fake.factory.FakePostFactory;
import com.tilmais.api.usecases.post.repository.InsertPostRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PublishPostUseCaseTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private PublishPostUseCase useCase;
  private InsertPostRepository repositoryMock;

  @BeforeEach
  void setUp() {
    this.repositoryMock = mock(InsertPostRepository.class);
    this.useCase = new PublishPostUseCase(this.repositoryMock, this.validator);
  }

  @Test
  @DisplayName("Should publish the post if doesn't has violations.")
  void shouldPublishPost() {
    var post = FakePostFactory.makeValidFakePost();
    when(this.repositoryMock.insertPost(any(Post.class))).thenReturn(Optional.of(post));

    var optional = this.useCase.publishPost(post);

    assertThat(optional).isPresent();
    verify(this.repositoryMock, times(1)).insertPost(any(Post.class));
  }

  @Test
  @DisplayName("Don't should publish the post if It has any violation.")
  void shouldDontPublishPost() {
    var post = FakePostFactory.makeInvalidFakePost();

    assertThatThrownBy(() -> this.useCase.publishPost(post))
        .isInstanceOf(PostViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).insertPost(any(Post.class));
  }
}