class Solution:
    def numberOfAlternatingGroups(self, colors: list[int], k: int) -> int:
        l = 0
        n = len(colors)
        res = 0
        size = n + k - 1
        while l < size:
            r = l + 1
            while r < size:
                if colors[r%n] == colors[(r-1)%n]:
                    break
                r += 1
            res += max(0, (r-l)-(k-1))
            l = r
        return res