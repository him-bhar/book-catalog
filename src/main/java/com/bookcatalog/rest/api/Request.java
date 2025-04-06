package com.bookcatalog.rest.api;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "messageType")
@JsonSubTypes(value = {
      @JsonSubTypes.Type(value = GetBookByIdRequest.class, name = GetBookByIdRequest.MESSAGE_TYPE),
      @JsonSubTypes.Type(value = SaveBookRequest.class, name = SaveBookRequest.MESSAGE_TYPE),
})
public interface Request extends Message {

}
