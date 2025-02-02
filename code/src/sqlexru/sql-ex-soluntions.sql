--
-- ## Exercise 1
--
-- Find the model number, speed and hard drive capacity for all the PCs with prices below $500.
--
-- Result set: model, speed, hd.
--

select model, speed, hd
from PC
where price < 500;


-- ---
--
-- ## Exercise 2
--
-- List all printer makers. Result set: maker.
--
--
-- It will not work, because all the Products might not
-- actual Printer in the market.
-- select distinct maker from Product, Printer
-- where Product.model = Printer.model;

select distinct maker from Product
where type = 'Printer';

-- ---
--
-- ## Exercise 3
--
-- Find the model number, RAM and screen size of the laptops with prices over $1000.

select model, ram, screen
from Laptop
where price > 1000;

--
-- ---
--
-- ## Exercise 4
--
-- Find all records from the Printer table containing data about color printers.
--

select *
from Printer
where color = 'y';


-- ---
--
-- ## Exercise 5
--
-- Find the model number, speed and hard drive capacity of PCs cheaper than $600 having a 12x or a 24x CD drive.
--

select model, speed, hd
from PC
where price < 600
and (cd = '12x' or cd = '24x');

-- ---
--
-- ## Exercise 6
--
-- For each maker producing laptops with a hard drive capacity of 10 Gb or higher, find the speed of such laptops. Result set: maker, speed.

select distinct maker, speed
from Product, Laptop
where Product.model = Laptop.model
and hd >= 10;

-- ## Exercise 7
-- Get the models and prices for all commercially
-- available products (of any type) produced by maker B.

with allCommercialProducts as (
select model, price from Printer
union
select model, price from PC
union
select model, price from Laptop
) select Product.model, price
  from allCommercialProducts, Product
where allCommercialProducts.model = Product.model
and maker = 'B';

-- ## Exercise 8
-- Find the makers producing PCs but not laptops.

with makersProducingPCs as
(
    select distinct maker from Product where type = 'PC'
), makersProducingLaptops as (
    select distinct maker from Product where type = 'Laptop'
) select *
from makersProducingPCs
where maker not in (
    select * from makersProducingLaptops
);

---

-- ## Exercise 9
-- Find the makers of PCs with a processor speed of 450 MHz or more. Result set: maker.

select distinct maker
from PC, Product
where PC.model = Product.model
and speed >= 450;

---

-- ## Exercise 10
-- Find the printer models having the highest price. Result set: model, price.

select distinct model, price
from Printer
where price = (
    select max(price) from Printer);

---

PRAGMA journal_mode = WAL;

PRAGMA journal_mode;

---

-- Triggers

drop table if exists employees;
drop table if exists salary_changes;
drop table if exists employees_deleted;

CREATE TABLE if not exists employees
(
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    salary INTEGER NOT NULL
);

CREATE TABLE if not exists salary_changes
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    employee_id INTEGER,
    old_salary INTEGER,
    new_salary INTEGER,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);


CREATE TRIGGER trigger_salary_update
    AFTER UPDATE ON employees
    FOR EACH ROW
    WHEN OLD.salary <> NEW.salary
BEGIN
    INSERT INTO salary_changes (employee_id, old_salary, new_salary)
    VALUES (OLD.id, OLD.salary, NEW.salary);
END;

INSERT INTO employees (id, name, salary) VALUES (1, 'Alice', 5000);

-- trigger_salary_update triggered on update (not insert)
UPDATE employees SET salary = 7000 WHERE id = 1;

SELECT * FROM salary_changes;

CREATE TABLE if not exists employees_deleted
(
    id INTEGER PRIMARY KEY,
    name TEXT,
    salary INTEGER,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER trigger_employee_delete
    AFTER DELETE ON employees
    FOR EACH ROW
BEGIN
    INSERT INTO employees_deleted (id, name, salary)
    VALUES (OLD.id, OLD.name, OLD.salary);
END;

delete from employees where id = 1;
