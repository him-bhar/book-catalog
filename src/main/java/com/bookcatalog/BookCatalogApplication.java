package com.bookcatalog;

import com.bookcatalog.config.DBConfig;
import com.bookcatalog.config.RequestAPIConfig;
import com.bookcatalog.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RequestAPIConfig.class, ServiceConfig.class, DBConfig.class})
public class BookCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

}
