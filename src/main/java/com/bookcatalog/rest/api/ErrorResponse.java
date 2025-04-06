package com.bookcatalog.rest.api;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ErrorResponse implements Response {
  @Override
  public String getMessageType() {
    return "ERROR_RESPONSE";
  }

  private String description;

}
