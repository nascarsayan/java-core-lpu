"""
https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/?envType=daily-question&envId=2025-02-26

1749. Maximum Absolute Sum of Any Subarray

This is Kadane's algorithm with a twist.
In Maximum Absolute Sum of subarray is essentially maximum of:
- Maximum Sum of subarray
- Maximum Sum of subarray with negative sign

We keep track of both positive and negative sum of subarray.
We update the maximum sum with the maximum of:
- Current positive sum
- Negative of current negative sum

If the positive sum becomes negative, we reset it to 0.
If the negative sum becomes positive, we reset it to 0.
"""

class Solution:
    def maxAbsoluteSum(self, nums: list[int]) -> int:
        res = 0
        pos, neg = 0, 0
        for num in nums:
            pos += num
            neg += num
            res = max([res, pos, -neg])
            if pos < 0:
                pos = 0
            if neg > 0:
                neg = 0
        return res
