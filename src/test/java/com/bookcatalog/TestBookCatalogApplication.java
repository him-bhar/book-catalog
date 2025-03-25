package com.bookcatalog;

import org.springframework.boot.SpringApplication;

public class TestBookCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.from(BookCatalogApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
