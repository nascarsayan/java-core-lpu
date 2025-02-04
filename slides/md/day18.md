---
theme: default
class: 'text-center'
transition: slide-left
title: Day 18
exportFilename: Day_18.pdf
mdc: true
---

# Day 18


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

- Segment Tree
- Problems on Segment Tree
  + https://leetcode.com/problems/range-sum-query-mutable/description/
  + https://leetcode.com/problems/range-frequency-queries/description/

---

## Use of Segment Tree

- Range (aggregate) Queries (Sum, Min, Max, etc.) over a subarray.
- The aggregate function should be associative and commutative.
- Range Updates (add a delta value / multiply by a value) over a subarray.

---

## Example

Given an array `arr`, what is the sum of elements in the range `[l, r]`?

12, 1, 17, 3, 15, 6, 9, 10

---

## Explanation

![segment Tree](../images/segmentTree.svg)

---

## Code

[Source Code](../../code/src/collections/SegmentTree.java)

