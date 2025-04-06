package com.bookcatalog.exporters;

import com.bookcatalog.domain.Book;
import lombok.SneakyThrows;
import org.apache.avro.Schema;
import org.apache.avro.data.TimeConversions;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.io.IOException;
import java.util.List;

public class BookParquetExporter {
  private static final String SCHEMA = """
        {
          "namespace": "com.bookcatalog.domain",
          "type": "record",
          "name": "Book",
          "fields": [
            {"name": "id", "type": "int"},
            {"name": "title", "type": "string"},
            {"name": "description", "type": "string"},
            {"name": "create_dt", "type": ["null", "string"], "default": null}
          ]
        }
        """;

  private static final Schema schema = new Schema.Parser().parse(SCHEMA);

  @SneakyThrows
  public byte[] exportToParquet(List<Book> books) {
    GenericData genericData = GenericData.get();
    genericData.addLogicalTypeConversion(new TimeConversions.DateConversion());
    ParquetOutputStream parquetOutputStream = new ParquetOutputStream();
    var records = books.stream().map(this::mapToRecord).toList();
    try (ParquetWriter<Object> writer = AvroParquetWriter.builder(parquetOutputStream)
          .withDataModel(genericData)
          .withSchema(schema)
          .withCompressionCodec(CompressionCodecName.SNAPPY)
          .withWriteMode(ParquetFileWriter.Mode.CREATE)
          .build()) {
      records.forEach(r -> {
        try {
          writer.write(r);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    }
    return parquetOutputStream.toArray();
  }

  public List<GenericRecord> mapToRecords(List<Book> books) {
    return books.stream().map(this::mapToRecord).toList();
  }

  private GenericRecord mapToRecord(Book book) {
    GenericRecord record = new GenericData.Record(schema);
    record.put("id", book.getBookId().intValue());
    record.put("title", book.getTitle());
    record.put("description", book.getDescription());
    record.put("create_dt", book.getCreateDt().toString());
    return record;
  }

}
