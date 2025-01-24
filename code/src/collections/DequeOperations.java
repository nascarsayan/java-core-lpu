package collections;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DequeOperations {
    public static void main(String[] args) {
        // 1. Creating Deques
        Deque<Integer> arrayDeque = new ArrayDeque<>();    // Preferred implementation
//        Deque<Integer> ll = new LinkedList<Integer>();   // collections.LinkedList implementation

        Queue<Integer> priorityQueue = new PriorityQueue<Integer>();

        // If we want thread-safe dequeue, we can use ConcurrentLinkedDeque.
        var x = new ConcurrentLinkedDeque<Integer>();

        // 2. Adding Elements
        // Front operations
        arrayDeque.addFirst(1);     // Throws exception if full
        arrayDeque.offerFirst(1);   // Returns false if full

        // Back operations
        arrayDeque.addLast(2);      // Throws exception if full
        arrayDeque.offerLast(2);    // Returns false if full

        // 3. Removing Elements
        // Front operations
        int first = arrayDeque.removeFirst();   // Throws exception if empty
        Integer front = arrayDeque.pollFirst(); // Returns null if empty

        // Back operations
        int last = arrayDeque.removeLast();    // Throws exception if empty
        Integer back = arrayDeque.pollLast();   // Returns null if empty

        // 4. Viewing Elements (without removal)
        // Front peek
        int firstElement = arrayDeque.getFirst();  // Throws exception if empty
        Integer frontElement = arrayDeque.peekFirst(); // Returns null if empty

        // Back peek
        int lastElement = arrayDeque.getLast();   // Throws exception if empty
        Integer backElement = arrayDeque.peekLast(); // Returns null if empty

        // 5. Stack Operations (LIFO)
        arrayDeque.push(5);         // Same as addFirst()
        int popped = arrayDeque.pop();  // Same as removeFirst()

        // 6. Queue Operations (FIFO)
        arrayDeque.offer(6);        // Same as offerLast()

        // can lead to null pointer exception.
        int v1 = arrayDeque.poll(); // Same as pollFirst()

        // handling Null as the value returned using poll.
        Integer v2 = arrayDeque.poll();
        if (v2 == null) {
            System.out.println("The value is null");
        } else {
            int v = v2;
        }

        // 7. Utility Operations
        int size = arrayDeque.size();
        boolean isEmpty = arrayDeque.isEmpty();
        arrayDeque.clear();         // Remove all elements
        boolean contains = arrayDeque.contains(5);
    }
}