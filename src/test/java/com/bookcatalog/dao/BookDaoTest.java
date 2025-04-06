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
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest(classes = { BookCatalogApplication.class })
//Comment next 2 lines to disable testcontainers and use h2 instead
@Import(TestcontainersConfiguration.class)
//@TestPropertySource(properties = { "spring.jooq.sql-dialect=POSTGRES" })
//Above line and dynamicpropertysource does the same thing, so comment that instead, above is another style
public class BookDaoTest {
  @Autowired
  private BookDao bookDao;

  @Test
  public void testCreate() {
    Book book = Book.builder().title("test-title").description("a sample for test")
          .createDt(ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))).build();
    Long bookId = bookDao.createBook(book);
    Assertions.assertAll(() -> MatcherAssert.assertThat(bookId, Matchers.notNullValue(Long.class)));
  }

  @Test
  public void testGetBookById() {
    Book book = Book.builder().title("test-title").description("a sample for test")
          .createDt(ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))).build();
    Long bookId = bookDao.createBook(book);
    Book bookFromDb = bookDao.getBookById(bookId.intValue());
    Assertions.assertAll(() -> MatcherAssert.assertThat(bookFromDb, Matchers.notNullValue(Book.class)),
          () -> MatcherAssert.assertThat(bookFromDb.getBookId(), Matchers.equalTo(bookId)));
  }

  @DynamicPropertySource
  static void registerPostgres(DynamicPropertyRegistry registry) {
    registry.add("spring.jooq.sql-dialect", () -> "POSTGRES");
  }
}
