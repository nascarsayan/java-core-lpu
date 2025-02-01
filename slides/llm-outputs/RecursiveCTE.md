This is my recursive table employees.

```sql
CREATE TABLE employees
(
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    manager_id INTEGER REFERENCES employees(id)
);
WITH RECURSIVE EmployeeHierarchy AS (
    SELECT id, name, manager_id, 0 AS level FROM employees WHERE name = 'Alice'
    UNION ALL
    SELECT e.id, e.name, e.manager_id, eh.level + 1
    FROM employees e
             JOIN EmployeeHierarchy eh ON e.manager_id = eh.id
)
SELECT * FROM EmployeeHierarchy;

INSERT INTO employees (id, name, manager_id) VALUES (1, 'Alice', null);
INSERT INTO employees (id, name, manager_id) VALUES (2, 'Bob', 1);
INSERT INTO employees (id, name, manager_id) VALUES (3, 'Charlie', 1);
INSERT INTO employees (id, name, manager_id) VALUES (4, 'David', 2);
INSERT INTO employees (id, name, manager_id) VALUES (5, 'Eve', 2);
INSERT INTO employees (id, name, manager_id) VALUES (6, 'Frank', 3);
```

### 1. `CREATE TABLE employees`
This part of the query creates a table called `employees` with the following columns:

- `id`: An integer that serves as the primary key for the table, ensuring that each employee has a unique identifier.
- `name`: A text field that holds the name of the employee, which cannot be `NULL` (because of the `NOT NULL` constraint).
- `manager_id`: An integer that references the `id` of another employee in the same table. It represents the employee's manager (or superior) within the organization. The `REFERENCES employees(id)` constraint establishes a foreign key relationship, indicating that the `manager_id` must match an existing `id` in the `employees` table, or it can be `NULL` if the employee has no manager.

### 2. `WITH RECURSIVE EmployeeHierarchy AS (...)`
This is a **Common Table Expression (CTE)** with a **recursive** query. The purpose of this CTE is to construct a hierarchical relationship of employees starting from a specific individual (in this case, the employee named 'Alice') and recursively finding all their subordinates (i.e., employees reporting to them directly or indirectly).

The CTE consists of two parts:

#### 2.1. The Base Case:
```sql
SELECT id, name, manager_id, 0 AS level
FROM employees
WHERE name = 'Alice'
```
- This part of the recursive CTE defines the base case: it selects the employee with the name 'Alice' from the `employees` table.
- It retrieves the `id`, `name`, and `manager_id` of Alice.
- The `0 AS level` assigns a level of 0 to Alice, indicating that she is at the top of the hierarchy.

#### 2.2. The Recursive Case:
```sql
UNION ALL
SELECT e.id, e.name, e.manager_id, eh.level + 1
FROM employees e
JOIN EmployeeHierarchy eh ON e.manager_id = eh.id
```
- This part defines the recursive query. It selects all employees (`e`) whose `manager_id` matches the `id` of an employee already in the `EmployeeHierarchy` CTE (`eh.id`).
- It joins the `employees` table with the `EmployeeHierarchy` CTE on the condition that the employee’s `manager_id` matches the `id` from the hierarchy.
- The `eh.level + 1` increments the level by 1 for each subsequent employee in the hierarchy, indicating that they are one level deeper than their manager.

The `UNION ALL` combines both the base case and the recursive case. The recursion continues until no more employees can be found (i.e., no more employees whose `manager_id` matches an `id` in the previous result set).

### 3. `SELECT * FROM EmployeeHierarchy;`
After the recursive CTE `EmployeeHierarchy` has been built, this final `SELECT` retrieves all the columns (`id`, `name`, `manager_id`, `level`) from the hierarchy. This will show all employees starting from 'Alice' and their subordinates at various levels.

### Summary:
This query creates a table of employees, builds a hierarchy starting from Alice, and recursively includes all employees reporting to her (either directly or indirectly). The final result will include the `id`, `name`, `manager_id`, and the `level` of each employee in the hierarchy, showing the structure of subordinates beneath Alice. The `level` indicates how far down the hierarchy each employee is.