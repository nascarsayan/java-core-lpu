package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subsets/description

public class Subsets {
    static class Solution {
        int[] nums;
        List<List<Integer>> subsets;

        void recurse(int numIdx, List<Integer> collected) {
            if (numIdx == nums.length) {
                subsets.add(new ArrayList<>(collected));
                return;
            }
            collected.add(nums[numIdx]);
            recurse(numIdx+1, collected);
            collected.removeLast();
            recurse(numIdx+1, collected);
        }

        public List<List<Integer>> subsets(int[] nums) {
            this.nums = nums;
            this.subsets = new ArrayList<>();
            recurse(0, new ArrayList<>());
            return this.subsets;
        }

        public static void main(String[] args) {
            int[] arr = {1,2,3};
            (new Solution()).subsets(arr);
        }
    }
}
