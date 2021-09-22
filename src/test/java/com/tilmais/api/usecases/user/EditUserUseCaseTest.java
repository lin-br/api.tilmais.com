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
import com.tilmais.api.domain.entities.User;
import com.tilmais.api.domain.entities.exceptions.UserViolationException;
import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
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
    var name = new Name(this.faker.name().fullName());
    var email = new EmailAddress(this.faker.internet().safeEmailAddress());
    var password = new Password(this.faker.internet().password());
    var user = new User(name, email, password);

    var newName = new Name("ok, the name was changed!");
    when(this.repositoryMock.updateUserName(anyString(), anyString())).thenReturn(Optional.of(new User(newName, null, null)));
    var optional = this.useCase.changeUserName(user, newName);

    assertThat(optional).isPresent();
    assertThat(newName.getFullName()).isEqualTo(optional.get().getName().getFullName());
    verify(this.repositoryMock, times(1)).updateUserName(anyString(), anyString());
  }

  @Test
  @DisplayName("Don't should change the user name when the new full name is null.")
  void shouldDontChangeUserNameIfNewNameIsNull() {
    var name = new Name(this.faker.name().fullName());
    var email = new EmailAddress(this.faker.internet().safeEmailAddress());
    var password = new Password(this.faker.internet().password());
    var user = new User(name, email, password);

    assertThatThrownBy(() -> this.useCase.changeUserName(user, new Name(null)))
        .isInstanceOf(UserViolationException.class)
        .hasMessageContaining("The name can't be null");
  }

  @Test
  @DisplayName("Don't should change the user name when the password into User is null.")
  void shouldDontChangeUserNameIfPasswordIsNull() {
    var name = new Name(this.faker.name().fullName());
    var email = new EmailAddress(this.faker.internet().safeEmailAddress());
    var user = new User(name, email, null);
    var newName = new Name("ok, the name was changed!");

    assertThatThrownBy(() -> this.useCase.changeUserName(user, newName))
        .isInstanceOf(UserViolationException.class)
        .hasMessageContaining("The password can't be null");
  }

  @Test
  @DisplayName("Don't should change the user name when the parameter is the same user's name.")
  void shouldDontCallUserRepositoryWhenTheNameInParameterIsTheSameName() {
    var name = new Name(this.faker.name().fullName());
    var email = new EmailAddress(this.faker.internet().safeEmailAddress());
    var password = new Password(this.faker.internet().password());
    var user = new User(name, email, password);

    var optional = this.useCase.changeUserName(user, name);

    verify(this.repositoryMock, never()).updateUserName(anyString(), anyString());
    assertThat(optional)
        .isPresent()
        .contains(user);
  }
}
