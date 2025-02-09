package leetcode.daily;

import java.util.HashMap;

public class NumberOfBadPairs {
    private static class Solution {
        public long countBadPairs(int[] nums) {
            // We need to count {(j - i) != (arr[j] - arr[i])}
            // i.e., all combos - {(j - i) == (arr[j] - arr[i])}
            // nCr(nums.length, 2) - {(j - i) == (arr[j] - arr[i])}
            // (j - i) == (arr[j] - arr[i]) is equivalent to
            // (arr[i] - i) == (arr[j] - j).
            // as we are visiting each index, we can find the count
            // till now how many arr[i] - i equal to the current arr[j] - j.
            // All those occur on the left of j. There is no over counting if you think.
            HashMap<Integer, Integer> freq = new HashMap<>();
            long goodPairs = 0;
            for (int i = 0; i < nums.length; i++) {
                goodPairs += freq.getOrDefault(nums[i]-i, 0);
                freq.merge(nums[i]-i, 1, Integer::sum);
            }
            long n = nums.length;
            long totalCombos = (n * (n-1)) / 2;
            return totalCombos - goodPairs;
        }
    }
}
