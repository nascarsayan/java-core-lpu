# 174 Dungeon Game
# https://leetcode.com/problems/dungeon-game/

class Solution:
    def calculateMinimumHP(self, dungeon: list[list[int]]) -> int:
        nr, nc = len(dungeon), len(dungeon[0])
        dp = [[float("inf")] * (nc+1) for _ in range(nr+1)]
        dp[-1][-2] = 1 # enter from bottom
        for ir in range(nr-1, -1, -1):
            for ic in range(nc-1, -1, -1):
                dp[ir][ic] = max(1, min(dp[ir+1][ic], dp[ir][ic+1])-dungeon[ir][ic])
        return int(dp[0][0])

print(Solution().calculateMinimumHP([[100]]))
