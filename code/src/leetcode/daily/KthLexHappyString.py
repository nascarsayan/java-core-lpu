"""
1415. The k-th Lexicographical String of All Happy Strings of Length n
https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/
"""

class Solution:
    def getHappyString(self, n: int, k: int) -> str:
        abc = ["a", "b", "c"]
        path = [-1] * n
        res = ""

        def recurse(pathIdx: int):
            nonlocal k
            nonlocal res
            if k < 0:
                return
            if pathIdx == n:
                k -= 1
                if k == 0:
                    res = "".join([abc[i] for i in path])
                return
            for i in range(len(abc)):
                if pathIdx > 0 and path[pathIdx - 1] == i:
                    continue
                path[pathIdx] = i
                recurse(pathIdx + 1)

        recurse(0)
        return res
