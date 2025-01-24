package leetcode;

import java.util.HashMap;

public class SubarraySumK {
    class Solution {
        public int subarraySum(int[] nums, int k) {
            HashMap<Integer, Integer> sumCount = new HashMap<>();
            var sumTillNow = 0;
            sumCount.put(0, 1);
            var res = 0;
            for (var num: nums) {
                sumTillNow += num;
                var numSubarraysEndingHere = sumCount.getOrDefault(sumTillNow - k, 0);
                res += numSubarraysEndingHere;

                // Increase sum by 1, if it exists, else set to 1.
                sumCount.compute(sumTillNow, (_, val) -> {
                    if (val == null) return 1;
                    return val+1;
                });
            }
            return res;
        }
    }
}
