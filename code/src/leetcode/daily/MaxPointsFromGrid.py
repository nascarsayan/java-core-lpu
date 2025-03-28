from collections import defaultdict
from typing import List
import heapq

# https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/?envType=daily-question&envId=2025-03-28

class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        nr, nc = len(grid), len(grid[0])
        qmx = max(queries)-1
        mx2Cnt = defaultdict(int)
        hp = [(grid[0][0], 0)]
        while hp:
            mx, irc = heapq.heappop(hp)
            ir, ic = irc // nc, irc % nc
            if grid[ir][ic] < 0:
                continue
            if mx > qmx:
                break
            mx2Cnt[mx] += 1
            grid[ir][ic] = -grid[ir][ic]
            for dr, dc in [(-1, 0), (0, 1), (1, 0), (0, -1)]:
                jr, jc = ir+dr, ic+dc
                if not (0 <= jr < nr and 0 <= jc < nc):
                    continue
                if grid[jr][jc] < 0:
                    continue
                heapq.heappush(hp, (max(mx, grid[jr][jc]), jr*nc+jc))

        mx2Cnt[float('inf')] = 0
        # mx, acc, idx
        q = sorted([[v-1, 0, i] for i, v in enumerate(queries)])
        iq = 0
        acc = 0
        for mx in sorted(mx2Cnt.keys()):
            while iq < len(q) and q[iq][0] < mx:
                q[iq][1] = acc
                iq += 1
            acc += mx2Cnt[mx]

        return [x[1] for x in sorted(q, key=lambda y: y[2])]
