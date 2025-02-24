"""
https://leetcode.com/problems/most-profitable-path-in-a-tree/description/
2467. Most Profitable Path in a Tree

We need to consider the sum from 0 to any leaf node.
Sum to reach any node v = Sum to reach parent of v + amount[v].
We can process the nodes in topological order, so that the cost to the parent is already calculated (kind of DP).
One small consideration is about the common path between Alice (at 0) and Bob (at bob). We need to empty the nodes till the mid-point of the path, and share the common node, if any.
"""

class Solution:
    def mostProfitablePath(
        self, edges: list[list[int]], bob: int, amount: list[int]
    ) -> int:
        nv = len(edges) + 1
        gr: list[list[int]] = [[] for _ in range(nv)]
        for u, v in edges:
            gr[u].append(v)
            gr[v].append(u)
        path: list[int] = []
        sharedPath: list[int] = []
        topo: list[int] = []

        def topoSort(u: int, parent: int):
            nonlocal path
            nonlocal sharedPath
            nonlocal topo
            path.append(u)
            if u == bob:
                sharedPath = path[::]
            for v in gr[u]:
                if v == parent:
                    continue
                topoSort(v, u)
            topo.append(u)
            path.pop()

        topoSort(0, -1)
        topo.reverse()
        
        l, r = 0, len(sharedPath) - 1
        while l <= r:
            if l == r:
                amount[sharedPath[r]] = amount[sharedPath[r]] // 2
                break
            amount[sharedPath[r]] = 0
            l += 1
            r -= 1
        res = float("-inf")
        for u in topo:
            for v in gr[u]:
                amount[v] += amount[u]
            if len(gr[u]) == 1 and u != 0:
                res = max(res, amount[u])
        return int(res)
