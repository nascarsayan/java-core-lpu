# https://leetcode.com/problems/find-the-punishment-number-of-an-integer/

from functools import cache

class Solution:
    def punishmentNumber(self, n: int) -> int:
        @cache
        def canMake(stream: str, remain: int):
            if remain == 0:
                return (not stream) or int(stream) == 0
            for i in range(1, len(stream)+1):
                num1 = int(stream[:i])
                if num1 > remain:
                    break
                if canMake(stream[i:], remain-num1):
                    return True
            return False

        res = 0
        for i in range(1, n+1):
            if canMake(str(i * i), i):
                res += i*i
        return res

if __name__ == "__main__":
    print(Solution().punishmentNumber(37))
