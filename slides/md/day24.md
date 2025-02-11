---
theme: default
class: 'text-center'
transition: slide-left
title: Day 24
exportFilename: Day_24.pdf
mdc: true
---

# Day 24


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

- Mixed Practice

---

## Z-algorithm

- [Z-algorithm](https://cp-algorithms.com/string/z-function.html)

---

## LeetCode Daily - [Remove all Occurences of a String](https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/)

We iterate over the string iteratively, until we find a substring match.
If we find a match, we remove the substring and continue the iteration.

To find the match, we perform Z-algorithm on `<pattern>#<text>` and check if any value is equal to `pattern.length`. The matched part should start after the `#`.
If we find a match, we chunk out that part and continue the iteration.

---
