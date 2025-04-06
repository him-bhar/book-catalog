package com.bookcatalog.rest.api;

import com.bookcatalog.domain.Book;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class SaveBookResponse implements Response {
  @Override
  public String getMessageType() {
    return "SAVE_BOOK_RESPONSE";
  }

  private Data data;

  @Builder
  @Getter
  public static class Data {
    private Book book;
  }
}
