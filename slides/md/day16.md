---
theme: default
class: 'text-center'
transition: slide-left
title: Day 16
exportFilename: Day_16.pdf
mdc: true
---

# Day 16


<div class="pt-13">
  <span @click="$slidev.nav.next" class="px-2 py-1 rounded cursor-pointer" flex="~ justify-center items-center gap-2" hover="bg-white bg-opacity-10">
    Press Space for next page <div class="i-carbon:arrow-right inline-block"></div>
  </span>
</div>

---
layout: default
---

## Table of contents

<Toc columns=3></Toc>

---

## Agenda

Opinionated SQL information:
  - How to choose your database
  - SQLite internals
    + Advanced concepts of SQLite
- SQL-ex.ru solutions 11-25

---

## SQLite advanced concepts

Reference: https://chatgpt.com/share/679d9c77-02e0-8009-a84c-b1e8521c76e6

---

## Index

Index allows us to access the pointer to those memory or disk locations which staisfy some parameter.
The parameter can be :
- integer
- string
- anything else which can be hashed.

In database terms, indexing can be applied to one or more columns of a table to quickly access the rows based on the values in those columns.

---

## Custom Functions in SQLite

We can create functions in other languages like C, C++, Python, etc. and register them in SQLite.

Example:

[sqlite_custom_func.py](../../code/src/sqlexru/sqlite_custom_func.py)

---
