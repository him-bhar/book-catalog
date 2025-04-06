package com.bookcatalog.rest.handlers;

import com.bookcatalog.rest.api.Request;
import com.bookcatalog.rest.api.Response;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class RequestRouter {
  private Map<Class<? extends Request>, RequestHandler<? extends Request, ? extends Response>> requestHandlers;

  public <R extends Request, S extends Response> void register(Class<R> requestClass, RequestHandler<R, S> responseFunction) {
    if (!Request.class.isAssignableFrom(requestClass)) {
      throw new IllegalArgumentException("Key must be a subclass of Request");
    }
    requestHandlers.put(requestClass, responseFunction);
  }

  public <REQ extends Request, RES extends Response> RES handle(REQ request) {
    Class<? extends Request> requestClass = request.getClass();
    RequestHandler<REQ, RES> handler = (RequestHandler<REQ, RES>) requestHandlers.get(requestClass);
    if (handler != null) {
      return handler.handle(request);
    } else {
      throw new UnsupportedOperationException("No handler found for message type " + request.getMessageType());
    }
  }
}
