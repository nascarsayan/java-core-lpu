"""
https://leetcode.com/problems/find-unique-binary-string/description/
1980. Find Unique Binary String
"""

class Solution:
    def findDifferentBinaryString(self, nums: list[str]) -> str:
        nums.sort()
        nidx = 0
        for i in range(2 ** len(nums[0])):
            if nidx == len(nums) or int(nums[nidx], 2) != i:
                return f"{i:0{len(nums[0])}b}"
            nidx += 1
        return ""
