---
theme: default
class: 'text-center'
transition: slide-left
title: Day 15
exportFilename: Day_15.pdf
mdc: true
---

# Day 15


<div class="pt-13">
  <span @click="$slidev.nav.next" class="px-2 py-1 rounded cursor-pointer" flex="~ justify-center items-center gap-2" hover="bg-white bg-opacity-10">
    Press Space for next page <div class="i-carbon:arrow-right inline-block"/>
  </span>
</div>

---
layout: default
---

## Table of contents

<Toc columns=3></Toc>

---

## Agenda

- Setup your IDE (Jetbrains) to run SQL queries locally (on SQLite).
  + [Jetbrains DataGrip](https://www.jetbrains.com/datagrip/)
  + [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/)
- Run the scripts under the [seed](../../code/src/sqlexru/seed/) folder to setup the tables and the data locally.
- Practice SQL queries on [SQL Exercises](https://www.sql-ex.ru/).

---

## How to build the query

1. We need all the info first.
  - We do `select *` from multiple tables.
  - We join the tables as needed.
2. We filter the data.
  - We use `where` clause to filter the data.
3. We project the data.
  - We use `select` clause to project the data.

---

[SQL-ex.ru Solutions](../../code/src/sql/sql-ex-soluntions.sql)

---
