package com.tilmais.api.usecases.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import com.tilmais.api.domain.entities.exceptions.UserViolationException;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.fake.factory.FakeUserFactory;
import com.tilmais.api.usecases.user.repository.UpdateUserRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EditUserUseCaseTest {

  private final Faker faker = new Faker();
  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private EditUserUseCase useCase;
  private UpdateUserRepository repositoryMock;

  @BeforeEach
  void setUp() {
    this.repositoryMock = mock(UpdateUserRepository.class);
    this.useCase = new EditUserUseCase(this.repositoryMock, this.validator);
  }

  @Test
  @DisplayName("Should change the user name when the new name is different that user.")
  void shouldChangeUserName() {
    var user = FakeUserFactory.makeValidFakeUser();
    var otherUser = FakeUserFactory.makeValidFakeUser(new Name("ok, the name was changed!"));
    when(this.repositoryMock.updateUserName(anyString(), anyString())).thenReturn(Optional.of(otherUser));

    var optional = this.useCase.changeUserName(user, otherUser.getName());

    assertThat(optional).isPresent()
        .hasValueSatisfying(userFromOptional -> {
          assertThat(userFromOptional.getName().getFullName()).isEqualTo(otherUser.getName().getFullName());
          assertThat(userFromOptional.getPassword()).isNotNull();
          assertThat(userFromOptional.getPosts()).isNotNull().isNotEmpty();
        });
    verify(this.repositoryMock, times(1)).updateUserName(anyString(), anyString());
  }

  @Test
  @DisplayName("Don't should change the user name when the new full name is null.")
  void shouldDontChangeUserNameIfNewNameIsNull() {
    var user = FakeUserFactory.makeValidFakeUser();

    assertThatThrownBy(() -> this.useCase.changeUserName(user, new Name(null)))
        .isInstanceOf(UserViolationException.class)
        .hasMessageContaining("The name can't be null");
  }

  @Test
  @DisplayName("Don't should change the user name when the password into User is null.")
  void shouldDontChangeUserNameIfPasswordIsNull() {
    var user = FakeUserFactory.makeInvalidFakeUserWithPasswordNull();
    var newName = new Name("the new name");

    assertThatThrownBy(() -> this.useCase.changeUserName(user, newName))
        .isInstanceOf(UserViolationException.class)
        .hasMessageContaining("The password can't be null");
  }

  @Test
  @DisplayName("Don't should change the user name when the parameter is the same user's name.")
  void shouldDontCallUserRepositoryWhenTheNameInParameterIsTheSameName() {
    var name = new Name(this.faker.name().fullName());
    var user = FakeUserFactory.makeValidFakeUser(name);

    var optional = this.useCase.changeUserName(user, name);

    verify(this.repositoryMock, never()).updateUserName(anyString(), anyString());
    assertThat(optional)
        .isPresent()
        .contains(user);
  }
}
