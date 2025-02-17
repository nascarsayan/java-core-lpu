class Solution:
    # TODO: TLE : fix it
    def constructDistancedSequence(self, n: int) -> list[int]:
        def recurse(prev: list[list[int]], path: list[int]) -> bool:
            if len(path) == 2*(n-1) + 1:
                return True
            for i in range(n, 0, -1):
                if i > 1:
                    if len(prev[i]) == 2:
                        continue
                    if len(prev[i]) == 1 and prev[i][0] + i != len(path):
                        continue
                else:
                    if len(prev[i]) == 1:
                        continue
                prev[i].append(len(path))
                path.append(i)
                if recurse(prev, path):
                    return True
                path.pop()
                prev[i].pop()
            return False
        prev: list[list[int]] = [[] for _ in range(n+1)]
        path: list[int] = []
        recurse(prev, path)
        return path

if __name__ == "__main__":
    print(Solution().constructDistancedSequence(11))
