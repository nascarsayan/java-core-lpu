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
- Union-Find or Disjoint Set Union

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

---

## Range Frequency Queries (using Prefix-Sum like approach)

- We could solve this problem using prefix-sum like approach.
  + However, the size complexity of the problem becomes `O(N*2)` if all elements are distinct - and can cause memory limit exceeded.

Code will look like this:

```java
// Will lead to memory limit exceeded.
class RangeFreqQuery {
    HashMap<Integer, Integer>[] freqTillIdx;
    public RangeFreqQuery(int[] arr) {
        freqTillIdx = new HashMap[arr.length+1];
        for (int i = 0; i <= arr.length; i++) {
            freqTillIdx[i] = new HashMap<>();
        }
        for (int i = 1; i <= arr.length; i++) {
            freqTillIdx[i].putAll(freqTillIdx[i-1]);
            freqTillIdx[i].merge(arr[i-1], 1, Integer::sum);
        }
    }

    public int query(int left, int right, int value) {
        var l = freqTillIdx[left];
        var r = freqTillIdx[right+1];
        return r.getOrDefault(value, 0) - l.getOrDefault(value, 0);
    }
}
```

---

## Range Frequency Queries (using Segment Tree)

- To reduce the size complexity, we can use Segment Tree. The memory complexity will be `O(N*log(N))`. [Reference](../llm-outputs/MemoryComplexityRangeFreq.md)

![segment Tree Frequency](../images/segTreeFreq.svg)

[Source Code](../../code/src/leetcode/RangeFrequencyQueries.java)

---

