package com.bookcatalog.rest.api;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class GetBookByIdRequest implements Request {

  public static final String MESSAGE_TYPE = "GET_BOOK_BY_ID_REQUEST";

  @Override
  public String getMessageType() {
    return MESSAGE_TYPE;
  }

  private int id;
}
