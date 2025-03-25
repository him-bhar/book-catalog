--liquibase formatted sql

--changeset himan:1 context:main
CREATE TABLE books
(
    book_id     INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

-- rollback drop table books;