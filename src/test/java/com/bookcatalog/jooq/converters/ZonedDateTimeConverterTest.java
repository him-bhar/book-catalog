package com.bookcatalog.jooq.converters;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class ZonedDateTimeConverterTest {
  ZonedDateTimeConverter zonedDateTimeConverter = new ZonedDateTimeConverter();

  @Test
  void testFrom() {
    LocalDateTime localDateTime = LocalDateTime.now();
    ZonedDateTime zonedDateTime = zonedDateTimeConverter.from(localDateTime);
    log.info("{}", localDateTime);
    log.info("{}", zonedDateTime);
    Assertions.assertEquals(localDateTime, zonedDateTimeConverter.to(zonedDateTime));
  }

  @Test
  void testTo() {
    var zone = ZoneId.of("Europe/London");
    ZonedDateTime zonedDateTime = ZonedDateTime.now(zone); //Should being regional/Zone
    LocalDateTime localDateTime = zonedDateTimeConverter.to(zonedDateTime);
    log.info("{}", zonedDateTime);
    log.info("{}", localDateTime);
    Assertions.assertEquals(zonedDateTime, zonedDateTimeConverter.from(localDateTime).withZoneSameInstant(zone));
  }
}
