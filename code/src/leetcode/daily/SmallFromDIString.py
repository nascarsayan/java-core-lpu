"""
2375. Construct Smallest Number From DI String
https://leetcode.com/problems/construct-smallest-number-from-di-string/description/?envType=daily-question&envId=2025-02-18
"""

class Solution:
    def smallestNumber(self, pattern: str) -> str:
        used = [False] * 10
        num = [0] * (len(pattern) + 1)

        def recurse(idx: int):
            nonlocal used
            nonlocal num
            if idx == len(num):
                return True
            st, fl = 1, 9
            if pattern[idx - 1] == "I":
                st = num[idx - 1] + 1
            else:
                fl = num[idx - 1] - 1
            for i in range(st, fl + 1):
                if used[i]:
                    continue
                used[i] = True
                num[idx] = i
                if recurse(idx + 1):
                    return True
                used[i] = False
            return False

        for i in range(1, 10):
            used[i] = True
            num[0] = i
            if recurse(1):
                return "".join(list(map(str, num)))
            used[i] = False
        return ""
