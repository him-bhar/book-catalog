package com.bookcatalog.rest.handlers;

import com.bookcatalog.rest.api.Request;
import com.bookcatalog.rest.api.Response;

public interface RequestHandler<REQUEST, RESPONSE> {
  RESPONSE handle(REQUEST request);
}
