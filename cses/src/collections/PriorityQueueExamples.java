package collections;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueExamples {

    public static void main(String[] args) {
        // 1. Basic Min Heap (Natural Ordering)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        System.out.println("Min value: " + minHeap.peek());  // 2

        // 2. Max Heap using Comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        System.out.println("Max value: " + maxHeap.peek());  // 8

        // 3. Custom Object Example
        class Task {
            String name;
            int priority;

            Task(String name, int priority) {
                this.name = name;
                this.priority = priority;
            }

            @Override
            public String toString() {
                return name + "(" + priority + ")";
            }
        }

        // Priority Queue with custom comparator for Task objects
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(
                (t1, t2) -> Integer.compare(t1.priority, t2.priority)
        );

        taskQueue.offer(new Task("Write report", 3));
        taskQueue.offer(new Task("Fix bug", 1));
        taskQueue.offer(new Task("Review code", 2));

        // Process tasks in priority order
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("Processing: " + task);
        }

        // 4. K Largest Elements Example
        PriorityQueue<Integer> kLargest = new PriorityQueue<>();  // min heap
        int k = 3;
        int[] nums = {10, 2, 5, 17, 7, 18, 6, 4};

        // kth item in the sorted order: 3
        // {2, 4, 5, 6, 7, 10, 17, 18};

        for (int num : nums) {
            kLargest.offer(num);
            if (kLargest.size() > k) {
                kLargest.poll();  // remove smallest
            }
        }
        System.out.println(k + " largest elements: " + kLargest);

        // Example ListNode class for merged lists
        class ListNode {
            int val;
            ListNode next;
            ListNode(int val) { this.val = val; }
        }

        // 5. Merge K Sorted Lists Example
        PriorityQueue<ListNode> mergeQueue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.val)
        );

        // Add first nodes of each list
        // mergeQueue.offer(list1.head);
        // mergeQueue.offer(list2.head);
        // mergeQueue.offer(list3.head);

        // Merge process would look like:
        while (!mergeQueue.isEmpty()) {
            ListNode current = mergeQueue.poll();
            // Add to result list
            if (current.next != null) {
                mergeQueue.offer(current.next);
            }
        }

        // 6. Common Operations
        PriorityQueue<String> pq = new PriorityQueue<>();

        // Basic operations
        pq.offer("Task 1");      // Add element
        pq.peek();               // View top element without removing
        pq.poll();               // Remove and return top element
        pq.size();               // Get queue size
        pq.isEmpty();            // Check if empty
        pq.clear();              // Remove all elements

        // Bulk operations
        pq.addAll(Arrays.asList("Task 2", "Task 3", "Task 4"));

        // Convert to array
        String[] array = pq.toArray(new String[0]);

        // Remove specific element
        pq.remove("Task 2");     // O(n) operation

        // Check if contains
        boolean contains = pq.contains("Task 3");  // O(n) operation
    }
}