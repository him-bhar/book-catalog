package com.bookcatalog.rest.api;

import com.bookcatalog.domain.Book;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class GetBookByIdResponse implements Response {
  @Override
  public String getMessageType() {
    return "GET_BOOK_BY_ID_RESPONSE";
  }

  private Data data;

  @Builder
  @Getter
  public static class Data {
    private Book book;
  }
}
