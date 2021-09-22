package com.tilmais.api.usecases.post;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.exceptions.PostViolationsException;
import com.tilmais.api.domain.entities.valueobjects.post.Body;
import com.tilmais.api.domain.entities.valueobjects.post.Title;
import com.tilmais.api.usecases.post.repository.InsertPostRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PublishPostUseCaseTest {

  private final Faker faker = new Faker();
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
    var title = new Title(this.faker.lorem().characters(20, 150));
    var body = new Body(this.faker.lorem().paragraph());
    var post = new Post(this.faker.code().gtin8(), title, body);
    when(this.repositoryMock.insertPost(any(Post.class))).thenReturn(Optional.of(post));

    var optional = this.useCase.publishPost(post);

    assertThat(optional).isPresent();
    verify(this.repositoryMock, times(1)).insertPost(any(Post.class));
  }

  @Test
  @DisplayName("Don't should publish the post if It has any violation.")
  void shouldDontPublishPost() {
    var post = new Post(this.faker.code().gtin8(), null, null);

    assertThatThrownBy(() -> this.useCase.publishPost(post))
        .isInstanceOf(PostViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).insertPost(any(Post.class));
  }
}