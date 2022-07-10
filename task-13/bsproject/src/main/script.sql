DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS warehouse;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books_authors;
DROP TABLE IF EXISTS orders_books;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS employees;

CREATE DATABASE bookstore;

CREATE TYPE status_book_type AS ENUM ('IN_STOCK', 'OUT_STOCK');

CREATE TYPE status_order_type AS ENUM ('NEW', 'COMPLETED', 'CANCELLED');

CREATE TABLE books
(
    id               serial PRIMARY KEY,
    price            int,
    isbn             varchar(32)      NOT NULL,
    name             varchar          NOT NULL,
    description      text,
    status           status_book_type NOT NULL,
    request_id       int REFERENCES requests (id),
    publication_date date
);

CREATE TABLE authors
(
    id     serial PRIMARY KEY,
    name   text NOT NULL,
    rating real
);

CREATE TABLE books_authors
(
    book_id   int REFERENCES books (id),
    author_id int REFERENCES authors (id),

    CONSTRAINT book_author_pk PRIMARY KEY (book_id, author_id)
);

CREATE TABLE orders_books
(
    order_id int REFERENCES orders (id),
    book_id  int REFERENCES books (id),

    CONSTRAINT order_book_pk PRIMARY KEY (order_id, book_id)
);

CREATE TABLE requests
(
    id     serial PRIMARY KEY,
    number int,
    name   varchar NOT NULL
);

CREATE TABLE orders
(
    id          serial PRIMARY KEY,
    price       int,
    status      status_order_type,
    customer_id integer REFERENCES customers (id) ON DELETE RESTRICT,
    employee_id integer REFERENCES employees (id) ON DELETE RESTRICT,
    issue_date  date
);

CREATE TABLE warehouse
(
    id serial PRIMARY KEY ,
    book_id int REFERENCES books (id),
    delivery_date  date
);

CREATE TABLE customers
(
    id   serial PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE employees
(
    id   serial PRIMARY KEY,
    name varchar NOT NULL
);


INSERT INTO books (price, name, isbn, description, publication_date, status)
VALUES (1200, '3 дня счастья', '978-5-699-89484-0', 'описание', '2010-09-12', 'OUT_STOCK'),
       (2340, 'Карма', '978-5-9268-2731-3', 'описание', '2013-09-12', 'OUT_STOCK'),
       (1200, 'Остров', '978-5-699-89484-0', 'описание', '2010-09-12', 'OUT_STOCK'),
       (1330, '1984', '924-5-9168-2731-3', 'описание', '2013-09-12', 'OUT_STOCK'),
       (510, 'ацук', '978-5-694-89484-0', 'описание', '2010-09-12', 'OUT_STOCK'),
       (3330, 'ьит', '978-5-9262-2731-3', 'описание', '2013-09-12', 'OUT_STOCK'),
       (1200, 'трап', '978-5-699-89484-0', 'описание', '2010-09-12', 'OUT_STOCK'),
       (2300, 'фвфыв', '971-5-9268-2731-3', 'описание', '2013-09-12', 'OUT_STOCK'),
       (7200, 'вф', '978-5-691-89584-0', 'описание', '2010-09-12', 'OUT_STOCK'),
       (2300, 'фвфыв', '978-5-9263-2731-3', 'описание', '2013-09-12', 'OUT_STOCK'),
       (1700, 'фыв', '978-5-271-23100-4', 'описание', '2020-09-12', 'OUT_STOCK');

INSERT INTO authors (name, rating)
VALUES ('Евсеев Михаил Максимович', 4.5),
       ('Семёнов Зураб Данилович', 4.1),
       ('Чикольба Яромир Платонович', 4.3),
       ('Михеев Оскар Михайлович', 3.5),
       ('Червоний Заур Романович', 4.6),
       ('Кабанов Тит Романович', 2.5),
       ('Рябов Юлиан Валериевич', 4.4),
       ('Мясников Харитон Григорьевич', 4.4);


INSERT INTO orders (price, status, issue_date)
VALUES (3540, 'NEW', '2010-09-12');

INSERT INTO customers (name)
VALUES ('name_1'),
       ('name_2'),
       ('name_3'),
       ('name_4');

INSERT INTO employees (name)
VALUES ('name_1'),
       ('name_2'),
       ('name_3'),
       ('name_4');