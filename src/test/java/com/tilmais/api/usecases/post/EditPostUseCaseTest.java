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
import com.tilmais.api.usecases.post.repository.UpdatePostRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EditPostUseCaseTest {

  private final Faker faker = new Faker();
  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private EditPostUseCase useCase;
  private UpdatePostRepository repositoryMock;

  @BeforeEach
  void setUp() {
    this.repositoryMock = mock(UpdatePostRepository.class);
    this.useCase = new EditPostUseCase(this.repositoryMock, this.validator);
  }

  @Test
  @DisplayName("Should update the post if doesn't has violations.")
  void shouldUpdatePost() {
    var title = new Title(this.faker.lorem().characters(20, 150));
    var body = new Body(this.faker.lorem().paragraph());
    var post = new Post(this.faker.code().gtin8(), title, body);
    when(this.repositoryMock.updatePost(any(Post.class))).thenReturn(Optional.of(post));

    var optional = this.useCase.editPost(post);

    assertThat(optional).isPresent();
    verify(this.repositoryMock, times(1)).updatePost(any(Post.class));
  }

  @Test
  @DisplayName("Don't should update the post if It has some violation.")
  void shouldDontUpdatePost() {
    var post = new Post(this.faker.code().gtin8(), null, null);

    assertThatThrownBy(() -> this.useCase.editPost(post))
        .isInstanceOf(PostViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).updatePost(any(Post.class));
  }
}