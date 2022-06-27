CREATE DATABASE bookstore_2;

CREATE TYPE status_book_type AS ENUM('IN_STOCK', 'OUT_STOCK');

CREATE TYPE status_order_type AS ENUM('NEW', 'COMPLETED', 'CANCELLED');

CREATE TABLE books (
    id serial PRIMARY KEY,
    price int,
    isbn varchar(32) NOT NULL,
    name varchar NOT NULL,
    description text,
    status status_book_type NOT NULL,
    request_id int REFERENCES requests(id),
    publication_date date,
    delivery_date date
);

CREATE TABLE authors (
    id serial PRIMARY KEY,
    name text NOT NULL,
    rating real
);

CREATE TABLE book_author (
    book_id int REFERENCES books(id),
    author_id int REFERENCES authors(id),

    CONSTRAINT book_author_pk PRIMARY KEY (book_id, author_id)
);

CREATE TABLE requests (
    id serial PRIMARY KEY,
    number int,
    name varchar NOT NULL
);

CREATE TABLE orders (
    id serial PRIMARY KEY,
    price int,
    status status_order_type,
    book_ids integer[],
    issue_date date
);

CREATE TABLE warehouse (
    id int,
    price int,
    isbn varchar(32) NOT NULL,
    name varchar NOT NULL,
    description text,
    status status_book_type NOT NULL,
    request_id int,
    publication_date date,
    delivery_date date
);

INSERT INTO books (price, name, isbn, description, publication_date, status, delivery_date)
VALUES (1200, '3 дня счастья', '978-5-699-89484-0', 'описание', '2010-09-12', 'OUT_STOCK', '2020-02-12'),
       (2340, 'Карма', '978-5-9268-2731-3', 'описание', '2013-09-12','OUT_STOCK', '2020-09-12'),
       (1200, 'Остров', '978-5-699-89484-0', 'описание', '2010-09-12', 'OUT_STOCK', '2020-02-13'),
       (1330, '1984', '924-5-9168-2731-3', 'описание', '2013-09-12','OUT_STOCK', '2020-09-12'),
       (510, 'Красное', '978-5-694-89484-0', 'описание', '2010-09-12', 'OUT_STOCK', '2021-09-12'),
       (3330, 'Тайнаа ываы', '978-5-9262-2731-3', 'описание', '2013-09-12','OUT_STOCK', '2020-09-12'),
       (1200, 'Подозреваются все', '978-5-699-89484-0', 'описание', '2010-09-12', 'OUT_STOCK', '2019-05-22'),
       (2300, 'фвфыв', '971-5-9268-2731-3', 'описание', '2013-09-12','OUT_STOCK', '2020-09-12'),
       (7200, 'вф', '978-5-691-89584-0', 'описание', '2010-09-12', 'OUT_STOCK', '2020-09-21'),
       (2300, 'фвфыв', '978-5-9263-2731-3', 'описание', '2013-09-12','OUT_STOCK', '2020-04-12'),
       (1700, 'фыв', '978-5-271-23100-4', 'описание', '2020-09-12','OUT_STOCK', '2021-02-12');

INSERT INTO authors (name, rating)
VALUES ('Евсеев Михаил Максимович', 4.5),
       ('Семёнов Зураб Данилович', 4.1),
       ('Чикольба Яромир Платонович', 4.3),
       ('Михеев Оскар Михайлович', 3.5),
       ('Червоний Заур Романович', 4.6),
       ('Кабанов Тит Романович', 2.5),
       ('Рябов Юлиан Валериевич', 4.4),
       ('Мясников Харитон Григорьевич', 4.4);


INSERT INTO orders (price, status, book_ids, issue_date)
VALUES (3540, 'NEW', '{1, 2}', '2010-09-12')