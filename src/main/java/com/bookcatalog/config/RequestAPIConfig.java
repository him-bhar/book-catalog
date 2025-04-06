package com.bookcatalog.config;

import com.bookcatalog.rest.ErrorHandler;
import com.bookcatalog.rest.RequestController;
import com.bookcatalog.rest.api.GetBookByIdRequest;
import com.bookcatalog.rest.api.SaveBookRequest;
import com.bookcatalog.rest.handlers.RequestRouter;
import com.bookcatalog.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RequestAPIConfig {

  @Bean
  public RequestRouter requestRouter(
        BookService bookService
  ) {
    RequestRouter router = new RequestRouter(new HashMap<>());
    router.register(GetBookByIdRequest.class, bookService::getBookById);
    router.register(SaveBookRequest.class, bookService::saveBook);
    return router;
  }


  @Bean
  public RequestController requestController(RequestRouter requestRouter) {
    return new RequestController(requestRouter);
  }

  @Bean
  public ErrorHandler errorHandler() {
    return new ErrorHandler();
  }

}
