---
theme: default
class: 'text-center'
transition: slide-left
title: Day 9
exportFilename: Day_09.pdf
mdc: true
---

# Day 9


<div class="pt-12">
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

- Dijkstra's Algorithm
- Kadane's Algorithm
- Grid Questions
- Other leetcode questions
- Leetcode Daily Question

---

## Dijkstra's Algorithm

```mermaid
graph LR
    A((1))
    B((2))
    C((3))
    D((4))
    E((5))
    A-->|4|B
    A-->|2|C
    B-->|3|D
    C-->|2|B
    C-->|6|E
    D-->|2|E
    B-->|3|E
```

[src code](../../cses/src/collections/Dijkstra.java)

![explanation](../images/dijkstra.svg)
