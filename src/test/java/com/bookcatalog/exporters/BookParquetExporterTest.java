package com.bookcatalog.exporters;

import com.bookcatalog.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

@Slf4j
public class BookParquetExporterTest {
  BookParquetExporter exporter = new BookParquetExporter();
  Random random = new Random();

  @Test
  void testExportToParquet() throws IOException {
    var books = List.of(create(), create(), create());
    byte[] byteArray = exporter.exportToParquet(books);
    Assertions.assertAll(() -> MatcherAssert.assertThat(byteArray, Matchers.notNullValue(byte[].class)));
    //Write bytearray to a temporary file
    /*Path testFile = Files.createFile(Path.of("./temp"));
    Files.write(testFile, byteArray);
    log.info(testFile.toAbsolutePath().toString());
    Files.deleteIfExists(testFile);*/
  }

  private Book create() {
    return Book.builder()
          .bookId((long) random.nextInt())
          .title("title".concat(UUID.randomUUID().toString()))
          .description("Desc.".concat(UUID.randomUUID().toString()))
          .createDt(ZonedDateTime.now()).build();
  }
}
