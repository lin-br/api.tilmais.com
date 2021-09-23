package com.tilmais.api.domain.entities;

import static org.assertj.core.api.Assertions.assertThat;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
import com.tilmais.api.fake.factory.FakeUserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  @DisplayName("Should is same user when the other has the same e-mail.")
  void shouldIsSameUser() {
    var email = new EmailAddress("teste@teste.com");
    var password = new Password("123456789");
    var name = new Name("full name");

    var user = User.newBuilder()
        .setName(name)
        .setEmailAddress(email)
        .setPassword(password)
        .build();
    var userTwo = User.newBuilder()
        .setName(name)
        .setEmailAddress(email)
        .setPassword(password)
        .build();

    assertThat(user).isEqualTo(userTwo);
  }

  @Test
  @DisplayName("Should is not same user when the other not has the same e-mail.")
  void shouldIsNotSameUser() {
    var email = new EmailAddress("teste@teste.com");
    var emailTwo = new EmailAddress("other@teste.com");
    var password = new Password("123456789");
    var name = new Name("full name");

    var user = User.newBuilder()
        .setName(name)
        .setEmailAddress(email)
        .setPassword(password)
        .build();
    var userTwo = User.newBuilder()
        .setName(name)
        .setEmailAddress(emailTwo)
        .setPassword(password)
        .build();

    assertThat(user).isNotEqualTo(userTwo);
  }

  @Test
  void shouldGetHashCode() {
    var user = FakeUserFactory.makeValidFakeUser();
    assertThat(user.hashCode()).isNotNull();
  }

  @Test
  @DisplayName("Should is not same user when the other is not an user.")
  void shouldIsNotSameUserWhenTheOtherIsNotUser() {
    var user = FakeUserFactory.makeValidFakeUser();

    assertThat(user).isNotEqualTo("userTwo");
  }

  @Test
  @DisplayName("Should is not same user when the other is null.")
  void shouldIsNotSameUserWhenTheOtherIsNull() {
    var user = FakeUserFactory.makeValidFakeUser();

    assertThat(user).isNotEqualTo(null);
  }
}
