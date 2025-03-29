from typing import List

# https://leetcode.com/problems/apply-operations-to-maximize-score/?envType=daily-question&envId=2025-03-29


class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        MOD = 10**9 + 7
        
        # Calculate prime scores more efficiently
        max_num = max(nums)
        prime_count = [0] * (max_num + 1)
        
        # Mark all numbers as having potential prime factors
        for i in range(2, max_num + 1):
            # If this number hasn't been marked yet, it's prime
            if prime_count[i] == 0:
                # For all multiples of this prime, increment their count
                for j in range(i, max_num + 1, i):
                    prime_count[j] += 1
        
        # Get prime scores for each number
        scores = [prime_count[num] for num in nums]
        
        # Calculate contribution of each position using monotonic stack
        # (all-in-one approach instead of separate left/right calculations)
        contribution = [0] * n
        stack = []
        
        # Calculate left boundaries in one pass
        for i in range(n):
            while stack and scores[stack[-1]] < scores[i]:
                stack.pop()
            left = stack[-1] + 1 if stack else 0
            stack.append(i)
            
            # Store partial contribution (will complete in next pass)
            contribution[i] = i - left + 1
            
        # Reset stack for right boundaries
        stack = []
        
        # Calculate final contributions
        for i in range(n-1, -1, -1):
            while stack and scores[stack[-1]] <= scores[i]:
                stack.pop()
            right = stack[-1] - 1 if stack else n-1
            stack.append(i)
            
            # Complete the contribution calculation: (i - left + 1) * (right - i + 1)
            contribution[i] *= (right - i + 1)
        
        # Create index-value-contribution tuples and sort by value (descending)
        indexed_values = [(nums[i], contribution[i]) for i in range(n)]
        indexed_values.sort(key=lambda x: x[0], reverse=True)
        
        # Calculate the result
        result = 1
        for num, count in indexed_values:
            use = min(k, count)
            if use > 0:
                # Use pow() for efficient modular exponentiation
                result = (result * pow(num, use, MOD)) % MOD
                k -= use
            if k == 0:
                break
                
        return result