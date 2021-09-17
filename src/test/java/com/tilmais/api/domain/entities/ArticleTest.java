package com.tilmais.api.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.tilmais.api.domain.entities.valueobjects.articles.Body;
import com.tilmais.api.domain.entities.valueobjects.articles.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArticleTest {

  @Test
  @DisplayName("Should is same article when the other article has the same code.")
  void shouldIsSameArticle() {
    var title = new Title("a text");
    var body = new Body("a other text");

    var article = new Article("code", title, body);
    var articleTwo = new Article("code", title, body);

    assertEquals(article, articleTwo);
  }

  @Test
  @DisplayName("Should is not same article when the other article don't has the same code.")
  void shouldIsNotSameArticle() {
    var title = new Title("a text");
    var body = new Body("a other text");

    var article = new Article("code", title, body);
    var articleTwo = new Article("other code", title, body);

    assertNotEquals(article, articleTwo);
  }
}
