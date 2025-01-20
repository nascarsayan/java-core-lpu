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

## Collections - ArrayList

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

## List Interface

List is an interface in Java. There are three concrete classes that implement the List interface:
- ArrayList
- LinkedList
- Vector

1. ArrayList vs Vector:
```java
ArrayList<Integer> arrayList = new ArrayList<>();
Vector<Integer> vector = new Vector<>();

// Key Differences:
// 1. Synchronization
vector.add(1);        // Thread-safe (synchronized)
arrayList.add(1);     // Not thread-safe

// 2. Growth
// Vector grows by 100% of its size
// ArrayList grows by 50% of its size

// 3. Performance
// ArrayList is faster due to no synchronization overhead
```

2. ArrayList vs LinkedList:
```java
ArrayList<Integer> arrayList = new ArrayList<>();
LinkedList<Integer> linkedList = new LinkedList<>();

// Performance Differences:
// 1. Random Access
arrayList.get(5);     // O(1) - Direct access
linkedList.get(5);    // O(n) - Must traverse

// 2. Insertion/Deletion at beginning/middle
arrayList.add(0, 1);  // O(n) - Must shift elements
linkedList.add(0, 1); // O(1) - Just update references

// 3. Memory
// ArrayList: Contiguous memory, less overhead per element
// LinkedList: Non-contiguous, extra memory for node references
```

3. Common List Implementations Comparison:

```java
// 1. ArrayList
ArrayList<Integer> arrayList = new ArrayList<>();
// Best for:
// - Random access
// - Iteration
// - Adding/removing at end
// - Fixed size operations
// Worst for:
// - Frequent insertions/deletions at beginning/middle
// - Unknown final size with many additions

// 2. LinkedList
LinkedList<Integer> linkedList = new LinkedList<>();
// Best for:
// - Frequent insertions/deletions at beginning/middle
// - Implementation of Queue/Deque
// - Memory not contiguous
// Worst for:
// - Random access
// - Memory efficiency

// 3. Vector (Legacy)
Vector<Integer> vector = new Vector<>();
// Best for:
// - Legacy code
// - Thread-safe operations without external synchronization
// Worst for:
// - Modern applications (use ArrayList with explicit synchronization)
// - Performance-critical code

// 4. CopyOnWriteArrayList
CopyOnWriteArrayList<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();
// Best for:
// - Read-heavy scenarios with rare modifications
// - Thread-safe iterations
// Worst for:
// - Write-heavy scenarios
// - Memory-constrained environments
```

Here's a performance comparison table:

```java
// Operation Performance Comparison

// 1. Add at end
arrayList.add(e);           // O(1) amortized
linkedList.add(e);          // O(1)
vector.add(e);             // O(1) amortized but synchronized

// 2. Add at index
arrayList.add(i, e);       // O(n)
linkedList.add(i, e);      // O(n) to find position, O(1) to insert
vector.add(i, e);         // O(n) but synchronized

// 3. Remove at index
arrayList.remove(i);       // O(n)
linkedList.remove(i);      // O(n) to find position, O(1) to remove
vector.remove(i);         // O(n) but synchronized

// 4. Get element
arrayList.get(i);         // O(1)
linkedList.get(i);        // O(n)
vector.get(i);           // O(1) but synchronized
```

Choosing the right implementation:

```java
// Choose ArrayList when:
if (needRandomAccess && !needThreadSafety && rareInsertionsInMiddle) {
    List<T> list = new ArrayList<>();
}

// Choose LinkedList when:
if (frequentInsertionsOrDeletions && !needRandomAccess) {
    List<T> list = new LinkedList<>();
}

// Choose Vector when:
if (needThreadSafety && workingWithLegacyCode) {
    List<T> list = new Vector<>();
}

// Choose CopyOnWriteArrayList when:
if (needThreadSafety && mostlyReadOperations) {
    List<T> list = new CopyOnWriteArrayList<>();
}
```

Memory usage considerations:
```java
// ArrayList:
// - Contiguous memory block
// - Size = (capacity * element_size) + overhead
// - Wastes memory when capacity >> size

// LinkedList:
// - Non-contiguous memory
// - Size = (size * (element_size + 2_references)) + overhead
// - No wasted capacity but more memory per element

// Vector:
// - Similar to ArrayList but with synchronization overhead
// - Additional memory for synchronization mechanisms

// CopyOnWriteArrayList:
// - Creates new array copy on each write
// - Higher memory usage during modifications
```

---

## Queue Interface

Queue is an interface in Java. There are three concrete classes that implement the Queue interface:
- LinkedList
- PriorityQueue
- ArrayDeque

---

### Difference between ArrayDeque and LinkedList

ArrayDeque:
- O(1) for add/remove/peek at both ends
- No capacity limit (grows as needed)
- More memory-efficient than LinkedList
- Not thread-safe
- Null elements not allowed
- Faster than Stack when used as a stack
- Faster than LinkedList when used as a queue

LinkedList as Deque:
- O(1) for all deque operations
- More memory overhead per element
- Allows null elements
- Better for frequent insertions/deletions

If we want a Java deque collection which is thread-safe, we can use `ConcurrentLinkedDeque`.

---

### Common Patterns using Dequeue

Common DSA Applications:

1. Sliding Window Problems:
```java
public static void slidingWindowMax(int[] nums, int k) {
    Deque<Integer> deque = new ArrayDeque<>();
    
    for (int i = 0; i < nums.length; i++) {
        // Remove elements outside current window
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }
        
        // Remove smaller elements
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }
        
        deque.offerLast(i);
        
        // Print max for current window
        if (i >= k - 1) {
            System.out.println(nums[deque.peekFirst()]);
        }
    }
}
```

2. BFS Implementation:
```java
public void bfs(Node root) {
    Deque<Node> queue = new ArrayDeque<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        Node current = queue.poll();
        System.out.println(current.val);
        
        if (current.left != null) queue.offer(current.left);
        if (current.right != null) queue.offer(current.right);
    }
}
```

3. Implementing Stack and Queue using Deque:
```java
class Stack<T> {
    private Deque<T> deque = new ArrayDeque<>();
    
    public void push(T item) { deque.addFirst(item); }
    public T pop() { return deque.removeFirst(); }
    public T peek() { return deque.peekFirst(); }
    public boolean isEmpty() { return deque.isEmpty(); }
}

class Queue<T> {
    private Deque<T> deque = new ArrayDeque<>();
    
    public void enqueue(T item) { deque.addLast(item); }
    public T dequeue() { return deque.removeFirst(); }
    public T peek() { return deque.peekFirst(); }
    public boolean isEmpty() { return deque.isEmpty(); }
}
```
