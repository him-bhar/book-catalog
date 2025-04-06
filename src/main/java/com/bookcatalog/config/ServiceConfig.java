package com.bookcatalog.config;

import com.bookcatalog.dao.BookDao;
import com.bookcatalog.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

  @Bean
  public BookService bookService(BookDao bookDao) {
    return new BookService(bookDao);
  }
}
