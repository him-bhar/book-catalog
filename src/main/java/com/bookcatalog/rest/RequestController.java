package com.bookcatalog.rest;

import com.bookcatalog.rest.api.Request;
import com.bookcatalog.rest.api.Response;
import com.bookcatalog.rest.handlers.RequestRouter;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RequestController {

  private RequestRouter requestRouter;

  @PostMapping(value = "/request", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> process(@RequestBody Request request) {
    return ResponseEntity.ok(requestRouter.handle(request));
  }
}
