package com.tilmais.api.usecases.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tilmais.api.domain.entities.Answer;
import com.tilmais.api.domain.entities.Comment;
import com.tilmais.api.domain.entities.Post;
import com.tilmais.api.domain.entities.exceptions.AnswerViolationsException;
import com.tilmais.api.domain.entities.exceptions.CommentViolationsException;
import com.tilmais.api.domain.entities.exceptions.PostViolationsException;
import com.tilmais.api.fake.factory.FakeAnswerFactory;
import com.tilmais.api.fake.factory.FakeCommentFactory;
import com.tilmais.api.fake.factory.FakePostFactory;
import com.tilmais.api.usecases.comment.repository.CreateCommentRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WriteCommentUseCaseTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private WriteCommentUseCase useCase;
  private CreateCommentRepository repositoryMock;

  @BeforeEach
  void setUp() {
    this.repositoryMock = mock(CreateCommentRepository.class);
    this.useCase = new WriteCommentUseCase(this.repositoryMock, this.validator);
  }

  @Test
  @DisplayName("Should write a comment on post.")
  void shouldWriteCommentOnPost() {
    var post = FakePostFactory.makeValidFakePost();
    var comment = FakeCommentFactory.makeFullValidFakeComment();
    when(this.repositoryMock.createCommentOnPost(any(Post.class), any(Comment.class)))
        .thenReturn(Optional.of(comment));

    var optional = this.useCase.writeCommentOnPost(post, comment);

    assertThat(optional)
        .isPresent()
        .hasValueSatisfying(commentFromOptional -> {
          assertThat(commentFromOptional.getAnswers()).isNotNull().isNotEmpty();
          assertThat(commentFromOptional.getCreated()).isNotNull().isEqualTo(comment.getCreated());
          assertThat(commentFromOptional.getEmailAddress()).isNotNull().isEqualTo(comment.getEmailAddress());
          assertThat(commentFromOptional.getName()).isNotNull().isEqualTo(comment.getName());
          assertThat(commentFromOptional.getNumber()).isNotNull().isEqualTo(comment.getNumber());
          assertThat(commentFromOptional.getText()).isNotNull().isEqualTo(comment.getText());
        });
    verify(this.repositoryMock, times(1)).createCommentOnPost(any(Post.class), any(Comment.class));
  }

  @Test
  @DisplayName("Should answer a comment.")
  void shouldAnswerComment() {
    var parent = FakeCommentFactory.makeValidFakeComment();
    var answer = FakeAnswerFactory.makeValidFakeAnswer(parent);
    parent.addAnswer(answer);
    when(this.repositoryMock.createAnswer(any(Comment.class), any(Answer.class)))
        .thenReturn(Optional.of(parent));

    var optional = this.useCase.answerComment(parent, answer);

    assertThat(optional)
        .isPresent()
        .hasValueSatisfying(commentFromOptional -> assertThat(commentFromOptional.getAnswers())
            .isNotNull()
            .isNotEmpty()
            .containsExactly(answer)
            .anySatisfy(answerFromOptional -> {
              assertThat(answerFromOptional.getParent()).isEqualTo(parent);
              assertThat(answerFromOptional.getCreated()).isEqualTo(answer.getCreated());
              assertThat(answerFromOptional.getNumber()).isEqualTo(answer.getNumber());
              assertThat(answerFromOptional.getText()).isEqualTo(answer.getText());
              assertThat(answerFromOptional.getName()).isEqualTo(answer.getName());
            }));
    verify(this.repositoryMock, times(1)).createAnswer(any(Comment.class), any(Answer.class));
  }

  @Test
  @DisplayName("Don't should write a comment on post when the post has violations.")
  void shouldDontWriteCommentOnPostIfPostHasViolations() {
    var invalidPost = FakePostFactory.makeInvalidFakePost();
    var comment = FakeCommentFactory.makeValidFakeComment();

    assertThatThrownBy(() -> this.useCase.writeCommentOnPost(invalidPost, comment))
        .isInstanceOf(PostViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).createCommentOnPost(any(Post.class), any(Comment.class));
  }

  @Test
  @DisplayName("Don't should write a comment on post when the comment has violations.")
  void shouldDontWriteCommentOnPostIfCommentHasViolations() {
    var post = FakePostFactory.makeValidFakePost();
    var invalidComment = FakeCommentFactory.makeInvalidFakeComment();

    assertThatThrownBy(() -> this.useCase.writeCommentOnPost(post, invalidComment))
        .isInstanceOf(CommentViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).createCommentOnPost(any(Post.class), any(Comment.class));
  }

  @Test
  @DisplayName("Don't should answer a comment when the parent comment has violations.")
  void shouldDontAnswerCommentIfParentCommentHasViolations() {
    var invalidParentComment = FakeCommentFactory.makeInvalidFakeComment();
    var answer = FakeAnswerFactory.makeValidFakeAnswer();

    assertThatThrownBy(() -> this.useCase.answerComment(invalidParentComment, answer))
        .isInstanceOf(CommentViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).createAnswer(any(Comment.class), any(Answer.class));
  }

  @Test
  @DisplayName("Don't should answer a comment when the answer comment has violations.")
  void shouldDontAnswerCommentIfAnswerCommentHasViolations() {
    var parent = FakeCommentFactory.makeValidFakeComment();
    var invalidAnswer = FakeAnswerFactory.makeInvalidFakeAnswer();

    assertThatThrownBy(() -> this.useCase.answerComment(parent, invalidAnswer))
        .isInstanceOf(AnswerViolationsException.class)
        .hasMessageContaining("can't be null");
    verify(this.repositoryMock, never()).createAnswer(any(Comment.class), any(Answer.class));
  }
}