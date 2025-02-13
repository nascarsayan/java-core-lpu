from typing import List
import heapq

class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        heapq.heapify(nums)
        ops = 0
        while(len(nums) >= 2 and nums[0] < k):
            v1 = heapq.heappop(nums)
            v2 = heapq.heappop(nums)
            heapq.heappush(nums, min(v1, v2) * 2 + max(v1, v2))
            ops += 1
        return ops
