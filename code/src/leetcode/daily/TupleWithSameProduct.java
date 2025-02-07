package leetcode.daily;

import java.util.HashMap;

public class TupleWithSameProduct {
    private static class Solution {
        public int tupleSameProduct(int[] nums) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            for (int i = 0; i < nums.length-1; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    freq.merge(nums[i]*nums[j], 1, Integer::sum);
                }
            }
            int res = 0;
            for (var cnt: freq.values()) {
                res += (cnt * (cnt-1)) * 4;
            }
            return res;
        }
    }
}
