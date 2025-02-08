package leetcode.daily;

// https://leetcode.com/problems/design-a-number-container-system/

import java.util.HashMap;
import java.util.TreeSet;

public class NumberContainerSystem {
    private static class NumberContainers {
        HashMap<Integer, Integer> index2Num;
        HashMap<Integer, TreeSet<Integer>> num2Indices;
        public NumberContainers() {
            index2Num = new HashMap<>();
            num2Indices = new HashMap<>();
        }

        public void change(int index, int number) {
            // Check what is present in the provided index.
            // If some number already exists, remove its traces.
            var current = index2Num.get(index);
            if (current != null) {
                num2Indices.get(current).remove(index);
            }
            // Save number at index.
            index2Num.put(index, number);
            // Save index in the sorted set of indices of the number.

            // First create new treeset if not present at this index.
            num2Indices.computeIfAbsent(number, _ -> new TreeSet<>());
            // add the index to the set.
            num2Indices.get(number).add(index);
        }

        public int find(int number) {
            var indices = num2Indices.get(number);
            if (indices == null || indices.isEmpty()) return -1;
            return indices.getFirst();
        }
    }
}
