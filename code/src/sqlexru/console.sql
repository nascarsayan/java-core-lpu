drop table if exists users;
drop table if exists articles;

create table users
(
    id       INTEGER PRIMARY KEY,
    name     TEXT NOT NULL,
    metadata JSON
);

INSERT INTO users (name, metadata)
VALUES ('Alice', '{
  "age": 25,
  "city": "New York",
  "skills": [
    "SQL",
    "Python"
  ],
  "movies": ["Catch me if you can", "After Sunset"]
}'),
       ('Bob', '{
         "age": 30,
         "city": "San Francisco",
         "skills": [
           "Java",
           "C++"
         ],
         "movies": ["Dark Knight", "Memento"]
       }');

SELECT id, name, json_extract(metadata, '$.city') AS city FROM users;

SELECT * FROM users WHERE json_extract(metadata, '$.age') > 27;

SELECT name, v1.value, v2.value AS skill
FROM users, json_each(metadata, '$.skills') as v1, json_each(metadata, '$.movies') as v2;


UPDATE users
SET metadata = json_set(metadata, '$.city', 'Los Angeles')
WHERE name = 'Alice';

--

-- Virtual Tables

CREATE VIRTUAL TABLE articles USING fts5(title, content);

INSERT INTO articles (title, content)
VALUES ('SQLite Basics', 'SQLite is a lightweight database engine.'),
       ('Full-Text Search in SQLite', 'FTS5 enables fast text searching in large documents.'),
       ('Advanced SQLite Features', 'SQLite supports JSON, indexing, and virtual tables.');


SELECT * FROM articles WHERE articles MATCH '"Full-Text Search"';

SELECT * FROM articles WHERE articles MATCH 'light*';

-- LIKE, GLOB, and REGEXP Do Not Work in FTS5
-- SELECT * FROM articles WHERE articles REGEXP '^light.*';

drop table if exists employees;
CREATE TABLE employees
(
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    manager_id INTEGER REFERENCES employees(id)
);

INSERT INTO employees (id, name, manager_id)
VALUES (1, 'Alice', NULL),
       (2, 'Bob', 1),
       (3, 'Charlie', 1),
       (4, 'David', 2),
       (5, 'Eve', 2),
       (6, 'Frank', 3);


-- CTE: Common Table expression

WITH EmployeeNames AS (
    SELECT id, name FROM employees WHERE manager_id IS NULL
)
SELECT * FROM EmployeeNames;

--

-- Recursive CTE
WITH RECURSIVE EmployeeHierarchy AS (
    SELECT id, name, manager_id, 0 AS level FROM employees WHERE name = 'Alice'
    UNION ALL
    SELECT e.id, e.name, e.manager_id, eh.level + 1
    FROM employees e
             JOIN EmployeeHierarchy eh ON e.manager_id = eh.id
)
SELECT * FROM EmployeeHierarchy;

---

-- INDEX

drop table if exists customers;
CREATE TABLE customers
(
   id INTEGER PRIMARY KEY,
   name TEXT NOT NULL,
   email TEXT UNIQUE NOT NULL,
   city TEXT,
   age INTEGER
);

INSERT INTO customers
    (name, email, city, age)
VALUES ('Alice', 'alice@example.com', 'New York', 25),
       ('Bob', 'bob@example.com', 'Los Angeles', 30),
       ('Charlie', 'charlie@example.com', 'Chicago', 35),
       ('David', 'david@example.com', 'New York', 40),
       ('Eve', 'eve@example.com', 'Los Angeles', 45);


CREATE INDEX idx_customers_city ON customers(city);

SELECT * FROM customers WHERE city = 'New York';

EXPLAIN QUERY PLAN SELECT * FROM customers WHERE city = 'New York';

drop table if exists orders;
CREATE TABLE orders
(
    id          INTEGER PRIMARY KEY,
    price       INTEGER NOT NULL,
    quantity    INTEGER NOT NULL,
    total_price INTEGER GENERATED ALWAYS AS (price * quantity) VIRTUAL
);

-- Stored or Virtual: Virtual means data is computed during SELECT.

INSERT INTO orders (price, quantity) VALUES (100, 5);

SELECT * FROM orders;
