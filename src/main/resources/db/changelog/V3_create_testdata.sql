--liquibase formatted sql

--changeset himan:3 context:test
insert into books (title, description, create_dt) values ('Book 1', 'Description 1', now());
insert into authors (author_name, book_id) values ('Author 1', 1);