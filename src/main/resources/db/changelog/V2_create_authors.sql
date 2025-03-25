--liquibase formatted sql

--changeset himan:2 context:main
CREATE TABLE AUTHORS
(
    author_id   INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(255) NOT NULL,
    book_id     INT,
    FOREIGN KEY (book_id) REFERENCES books (book_id)
);

-- rollback drop table books;