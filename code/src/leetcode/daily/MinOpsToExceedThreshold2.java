package leetcode.daily;

import java.util.PriorityQueue;

public class MinOpsToExceedThreshold2 {

    private static class Solution {
        public int minOperations(int[] nums, int k) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (long num : nums) {
                pq.offer(num);
            }
            int ops = 0;

            while (pq.size() >= 2 && pq.peek() < k) {
                var v1 = pq.poll();
                var v2 = pq.poll();
                pq.offer(Math.min(v1, v2) * 2 + Math.max(v1, v2));
                ops++;
            }

            return ops;
        }

        public static void main(String[] args) {
            int[] nums = {1000000000,999999999,1000000000,999999999,1000000000,999999999};
            var res = (new Solution()).minOperations(nums, 1000000000);
            System.out.println(res);
        }
    }

}

