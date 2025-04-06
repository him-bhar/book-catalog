package com.bookcatalog.jooq.converters;

import org.jooq.impl.AbstractConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeConverter extends AbstractConverter<LocalDateTime, ZonedDateTime> {
  public ZonedDateTimeConverter() {
    super(LocalDateTime.class, ZonedDateTime.class);
  }

  //LocalDatetime will always be in UTC equivalent, so we need to just convert it back.
  @Override
  public ZonedDateTime from(LocalDateTime localDateTime) {
    return localDateTime.atZone(ZoneId.of("UTC"));
  }

  @Override
  public LocalDateTime to(ZonedDateTime zonedDateTime) {
    ZonedDateTime utcDate = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    return utcDate.toLocalDateTime();
  }
}
