---
theme: default
class: 'text-center'
transition: slide-left
title: Day 22
exportFilename: Day_22.pdf
mdc: true
---

# Day 22


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

- Mixed practice

---

## [Design a Number Container System](https://leetcode.com/problems/design-a-number-container-system/description/?envType=daily-question&envId=2025-02-08)

![Explanation](../images/numberContainerSystem.svg)

[source code](../../code/src/leetcode/NumberContainerSystem.java)

---

## Concurrency

Tip: State should not be maintained within the locks themselves, but we should use some variables to maintain the state.

[codes](../../code/src/leetcode/concurrency/)

---

## Backtracking

Solve puzzles from River Crossing IQ

https://www.youtube.com/watch?v=r3DfWNxXhZc

---

## River Crossing: [WolfSheepCabbage](https://www.youtube.com/watch?v=r3DfWNxXhZc)

![explanation](../images/riverCrossing.svg)

[source code](../../code/src/river_crossing/P01WolfSheepCabbage.java)

---

