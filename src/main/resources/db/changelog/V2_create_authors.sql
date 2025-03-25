--liquibase formatted sql

--changeset himan:2 context:main
CREATE TABLE AUTHORS
(
    author_id   serial PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    book_id     INT,
    FOREIGN KEY (book_id) REFERENCES books (book_id)
);

-- rollback drop table books;