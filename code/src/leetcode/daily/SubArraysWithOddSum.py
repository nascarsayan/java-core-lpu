"""
https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/?envType=daily-question&envId=2025-02-25
1524. Number of Sub-arrays With Odd Sum

We take the prefix sum `Pre[m]` for all indices.
A subarray [A_m..A_n] will have odd sum if Pre[m] and Pre[n] have different parity.
Ans = (number of ways to get odd prefix sum) * (number of ways to get even prefix sum).
"""

class Solution:
    def numOfSubarrays(self, arr: list[int]) -> int:
        freq = [1, 0]
        curr = 0
        for v in arr:
            curr = (curr + v) % 2
            freq[curr] += 1
        return (freq[0] * freq[1]) % (10**9 + 7)
