package leetcode.daily;

import java.util.HashMap;

public class MaxSumPairEqDigitSum {
    private static class Solution {
        private int getDigitSum(int num) {
            int s = 0;
            while (num > 0) {
                s += num % 10;
                num /= 10;
            }
            return s;
        }
        public int maximumSum(int[] nums) {
            // We just need to store the maximum number among
            // all the numbers with the same digit sum.
            HashMap<Integer, Integer> sum2MaxNum = new HashMap<>();
            int mx = -1;
            for (var num: nums) {
                var s = getDigitSum(num);
                var other = sum2MaxNum.get(s);
                if (other != null) {
                    mx = Math.max(mx, num+other);
                }
                sum2MaxNum.merge(s, num, Math::max);
            }
            return mx;
        }
    }
}
