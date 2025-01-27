package leetcode.daily;

import java.util.*;

// https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/description/
// 2948. Make Lexicographically Smallest Array by Swapping Elements

public class LexicoSmallestArray {
    static class Solution {
        public int[] lexicographicallySmallestArray(int[] nums, int limit) {
            int[] sortedNums = nums.clone();
            Arrays.sort(sortedNums);
            var num2Group = new HashMap<Integer, Integer>();
            var group2Nums = new HashMap<Integer, ArrayDeque<Integer>>();

            int group = 0;
            num2Group.put(sortedNums[0], group);
            group2Nums.put(group, new ArrayDeque<>(List.of(sortedNums[0])));
            for (int i = 1; i < sortedNums.length; i++) {
                if (sortedNums[i] - sortedNums[i-1] > limit) {
                    group++;
                    group2Nums.put(group, new ArrayDeque<>());
                }
                num2Group.put(sortedNums[i], group);
                group2Nums.get(group).addLast(sortedNums[i]);
            }

            for (int i = 0; i < nums.length; i++) {
                var g = num2Group.get(nums[i]);
                nums[i] = group2Nums.get(g).pollFirst();
            }

            return nums;
        }

        public static void main(String[] args) {
            int[] nums = {4,52,38,59,71,27,31,83,88,10};
            var numsNew = (new Solution()).lexicographicallySmallestArray(nums, 14);
            for (var num: numsNew) System.out.println(num);
        }
    }
}
