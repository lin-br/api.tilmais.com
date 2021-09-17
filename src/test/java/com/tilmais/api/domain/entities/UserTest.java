package com.tilmais.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.tilmais.api.domain.entities.valueobjects.EmailAddress;
import com.tilmais.api.domain.entities.valueobjects.Name;
import com.tilmais.api.domain.entities.valueobjects.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  @DisplayName("Should is same user when the other has the same e-mail.")
  void shouldIsSameUser() {
    var email = new EmailAddress("teste@teste.com");
    var password = new Password("123456789");
    var name = new Name("full name");

    var user = new User(name, email, password);
    var userTwo = new User(name, email, password);

    assertEquals(user, userTwo);
  }

  @Test
  @DisplayName("Should is not same user when the other not has the same e-mail.")
  void shouldIsNotSameUser() {
    var email = new EmailAddress("teste@teste.com");
    var emailTwo = new EmailAddress("other@teste.com");
    var password = new Password("123456789");
    var name = new Name("full name");

    var user = new User(name, email, password);
    var userTwo = new User(name, emailTwo, password);

    assertNotEquals(user, userTwo);
  }
}
