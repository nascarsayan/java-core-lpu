# https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/?envType=daily-question&envId=2025-04-05

class Solution:
    def subsetXORSum(self, nums: list[int]) -> int:
        res = 0
        n = len(nums)
        def recurse(idx: int, x: int):
            nonlocal res
            if idx == n:
                res += x
                return
            recurse(idx+1, x^nums[idx])
            recurse(idx+1, x)
        recurse(0, 0)
        return res
