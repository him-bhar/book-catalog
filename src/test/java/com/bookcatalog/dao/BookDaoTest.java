package com.bookcatalog.dao;

import com.bookcatalog.BookCatalogApplication;
import com.bookcatalog.TestcontainersConfiguration;
import com.bookcatalog.domain.Book;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { BookCatalogApplication.class })
//Comment next 2 lines to disable testcontainers and use h2 instead
@Import(TestcontainersConfiguration.class)
@TestPropertySource(properties = { "spring.jooq.sql-dialect=POSTGRES" })
public class BookDaoTest {
  @Autowired
  private BookDao bookDao;

  @Test
  public void testCreate() {
    Book book = Book.builder().title("test-title").description("a sample for test").build();
    Long bookId = bookDao.createBook(book);
    Assertions.assertAll(() -> MatcherAssert.assertThat(bookId, Matchers.notNullValue(Long.class)));
  }
}
