package com.bookcatalog.service;

import com.bookcatalog.dao.BookDao;
import com.bookcatalog.domain.Book;
import com.bookcatalog.rest.api.GetBookByIdRequest;
import com.bookcatalog.rest.api.GetBookByIdResponse;
import com.bookcatalog.rest.api.SaveBookRequest;
import com.bookcatalog.rest.api.SaveBookResponse;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@AllArgsConstructor
public class BookService {

  private BookDao bookDao;

  public GetBookByIdResponse getBookById(GetBookByIdRequest request) {
    return GetBookByIdResponse.builder()
          .data(
                GetBookByIdResponse.Data.builder()
                      .book(bookDao.getBookById(request.getId()))
                      .build()
          ).build();
  }

  @Transactional
  public SaveBookResponse saveBook(SaveBookRequest request) {
    var requestedBook = Book.builder().title(request.getTitle())
          .description(request.getDescription())
          .createDt(ZonedDateTime.now())
          .build();
    var bookId = bookDao.createBook(requestedBook);
    return SaveBookResponse.builder()
          .data(
                SaveBookResponse.Data.builder()
                      .book(bookDao.getBookById(bookId.intValue()))
                      .build()
          ).build();
  }

}
