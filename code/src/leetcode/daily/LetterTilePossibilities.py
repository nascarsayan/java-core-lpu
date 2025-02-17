from collections import Counter
from math import factorial

class Solution:
    def numTilePossibilities(self, tiles: str) -> int:
        cnt = Counter(tiles)
        chars = list(cnt.keys())
        freqs: list[int] = [0] * len(chars)
        res = 0
        def recurse(idx: int):
            nonlocal res
            if idx == len(chars):
                ways = factorial(sum(freqs))
                for f in freqs:
                    ways //= factorial(f)
                res += ways
                return
            for f in range(cnt[chars[idx]]+1):
                freqs[idx] = f
                recurse(idx+1)
        recurse(0)
        return res-1

if __name__ == "__main__":
    print(Solution().numTilePossibilities("AAB"))
