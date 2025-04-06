package com.bookcatalog.rest.api;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
@ToString
public class SaveBookRequest implements Request {
  public static final String MESSAGE_TYPE = "SAVE_BOOK_REQUEST"; //MESSAGE

  @Override
  public String getMessageType() {
    return MESSAGE_TYPE;
  }

  private String title;
  private String description;

}
