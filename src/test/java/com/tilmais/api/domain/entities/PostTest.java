package com.tilmais.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.tilmais.api.domain.entities.valueobjects.post.Body;
import com.tilmais.api.domain.entities.valueobjects.post.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

  @Test
  @DisplayName("Should is same article when the other article has the same code.")
  void shouldIsSameArticle() {
    var title = new Title("a text");
    var body = new Body("a other text");

    var article = new Post("code", title, body);
    var articleTwo = new Post("code", title, body);

    assertEquals(article, articleTwo);
  }

  @Test
  @DisplayName("Should is not same article when the other article don't has the same code.")
  void shouldIsNotSameArticle() {
    var title = new Title("a text");
    var body = new Body("a other text");

    var article = new Post("code", title, body);
    var articleTwo = new Post("other code", title, body);

    assertNotEquals(article, articleTwo);
  }
}
