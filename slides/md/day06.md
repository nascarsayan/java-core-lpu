---
theme: default
class: 'text-center'
transition: slide-left
title: Day 6
exportFilename: Day_06.pdf
mdc: true
---

# Day 6

## There is no magic, every convenience is just an abstraction

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

## Collections

```java
package collections;

import java.util.*;

public class ArrayListOperations {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        // another list of 5 items all set to 10.
        var anotherList = new ArrayList<>(Collections.nCopies(5, 10));

        // 1. Adding elements
        list.add(10);                  // Adds to end: O(1) amortized
        list.add(1, 5);               // Adds at index: O(n)
        list.addFirst(15);    // Same complexity as above: O(n)
        list.addAll(anotherList);     // Adds all elements from another list: O(n)

        // 2. Accessing elements
        int element = list.get(0);     // Get element at index: O(1)
        int size = list.size();        // Get size: O(1)
        boolean exists = list.contains(5); // Check if element exists: O(n)
        int index = list.indexOf(52321);   // Find first occurrence: O(n)
        int lastIndex = list.lastIndexOf(5); // Find last occurrence: O(n)

        // 3. Modifying elements
        list.set(1, 15);              // Replace element at index: O(1)
//        list[5] = 10; // will not work.

        list.add(5, 15);
        // 4. Removing elements
        list.remove(3);               // Remove by index: O(n)
        list.removeFirst();
        list.remove(Integer.valueOf(10)); // Remove by value: O(n)
        // list.clear();                 // Remove all elements: O(n)

        // 5. Common operations in DSA
        // Sorting
        Collections.sort(list);       // Sort in ascending: O(n log n)
        Collections.reverse(list);    // Reverse the list: O(n)

        // Sublist
        List<Integer> subList = list.subList(1, 4); // Get elements from index 1 to 3

        // Converting to array
        var arr = list.toArray(new Integer[0]);

        // 6. Iteration
        // Using for-each
        for (Integer num : list) {
            System.out.println(num);
        }

        // Using iterator
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 7. Useful methods for DSA
        Collections.max(list);        // Find maximum element: O(n)
        Collections.min(list);        // Find minimum element: O(n)
        Collections.frequency(list, 5); // Count occurrences: O(n)

        list.add(-5);
        list.add(-10);
        list.add(10);

        var k = Collections.binarySearch(list, 5); // Binary search (requires sorted list): O(log n)
        System.out.println(k);
    }
}
```
