package com.bookcatalog.dao;

import com.bookcatalog.domain.Book;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.ResultQuery;

import static com.bookcatalog.database.Tables.BOOKS;

@AllArgsConstructor
public class BookDao {
  private DSLContext dsl;

  public Long createBook(Book book) {
    ResultQuery<?> query = dsl.insertInto(BOOKS, BOOKS.TITLE, BOOKS.DESCRIPTION, BOOKS.CREATE_DT)
          .values(book.getTitle(), book.getDescription(), book.getCreateDt())
          .returning(BOOKS.BOOK_ID);
    Book record = query.fetchOneInto(Book.class);
    return record.getBookId();
  }

  public Book getBookById(Integer id) {
    return dsl.select(BOOKS.fields()).from(BOOKS).where(BOOKS.BOOK_ID.eq(id)).fetchOneInto(Book.class);
  }
}
