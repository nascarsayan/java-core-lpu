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

[src code](../../code/src/collections/Dijkstra.java)

![explanation](../images/dijkstra.svg)

## Kadane's Algorithm

[src code](../../code/src/collections/Kadane.java)

![explanation](../images/kadane.svg)

---

## Today's Leetcode

https://leetcode.com/problems/count-servers-that-communicate/description/?envType=daily-question&envId=2025-01-23

---

## Valid Parenthesis

https://leetcode.com/problems/valid-parentheses/description/

[src code](../../code/src/leetcode/ValidParenthesis.java)

---

## Generate Parenthesis

https://leetcode.com/problems/generate-parentheses/description/

---

## Binary Search

![explanation](../images/binary_search.svg)

---

### **Binary Search on Monotonic True-False Arrays**

#### **Key Idea**
- Many problems can be reduced to finding a **transition point** in a **monotonic true-false array**.
- Binary search efficiently finds this transition point in **O(log n)** time.

---

#### **What is a Monotonic True-False Array?**
An array where:
- All `false` values come before all `true` values (monotonic increasing), or
- All `true` values come before all `false` values (monotonic decreasing).

Example:
```
[false, false, false, true, true, true]
```
The transition point is where `false` changes to `true`.

---

#### **How Binary Search Works**
1. **Define the Condition**:
   - Use a function `check(x)` that returns `true` or `false` based on a condition.
   - Example: `check(x)` could represent "is this version bad?" or "is this value valid?"

2. **Set Up the Search Space**:
   - Initialize `low` and `high` to cover the entire range.

3. **Find the Transition Point**:
   - Use binary search to narrow down the range:
     - If `check(mid)` is `true`, move left (`high = mid - 1`).
     - If `check(mid)` is `false`, move right (`low = mid + 1`).
   - The transition point is at `low` (or `high`, depending on the problem).

---

#### **Example: Finding the First Valid Value**

Suppose you have an array of values, and you want to find the **first value** where `check(x)` returns `true`.

```python
def findFirstValid(n):
    low, high, ans = 0, n - 1, 0
    while low <= high:
        mid = low + (high - low) // 2
        if check(mid):  # Transition point is in the left half
            ans = mid
            high = mid - 1
        else:           # Transition point is in the right half
            low = mid + 1
    return ans
```

---

#### **Why This Works**
- The array is **monotonic**: `check(x)` transitions from `false` to `true` (or vice versa).
- Binary search halves the search space at each step, making it **O(log n)**.

---

#### **Applications**
1. **First Bad Version**:
   - Find the first version where `check(x)` (e.g., "is this version bad?") returns `true`.

2. **Peak Finding**:
   - Find the peak in a mountain-like array.

3. **Search in Rotated Sorted Array**:
   - Find the pivot point where the array is rotated.

---

## Binary Search Application

Time complexity of Binary Search: `O(log (n))`
In some problems, we need to do the following:
- Search for a value of `n` within the range `x` to `y`. Time complexity to search for the value becomes `O(log(y-x))`, i,e., `O(log(range of the possible values))`.
- For each value of `n`, we can check if `n` satisfies some condition in `O(m)` time.
- The overall complexity becomes `O(log(range) * m)`.
- We'll look into concrete examples.

---

## Binary Search Problems

Reference: https://cp-algorithms.com/num_methods/binary_search.html

*   [LeetCode - Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
*   [LeetCode - Search Insert Position](https://leetcode.com/problems/search-insert-position/)
*   [LeetCode - First Bad Version](https://leetcode.com/problems/first-bad-version/)
*   [LeetCode - Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square/)
*   [LeetCode - Find Peak Element](https://leetcode.com/problems/find-peak-element/)
*   [LeetCode - Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
*   [LeetCode - Find Right Interval](https://leetcode.com/problems/find-right-interval/)

---

# 33. Search in Rotated Sorted Array

https://leetcode.com/problems/search-in-rotated-sorted-array/

[src code](../../code/src/leetcode/FindInRotatedSorted.java)

---

## Homework (Tomorrow)

- https://cses.fi/problemset/task/1620
- https://cses.fi/problemset/task/1085
