package leetcode.daily;

import java.util.HashMap;
import java.util.HashSet;

public class MakingALargeIsland {
    private static class Solution {
        private int[][] grid;
        private int nr, nc;
        private static class Loc {
            int ir, ic;
            Loc(int ir, int ic) {
                this.ir = ir;
                this.ic = ic;
            }
            Loc next(Loc cardinal) {
                return new Loc(this.ir + cardinal.ir, this.ic + cardinal.ic);
            }
            boolean isValid(int nr, int nc) {
                if (!(0 <= ir && ir < nr)) return false;
                if (!(0 <= ic && ic < nc)) return false;
                return true;
            }
        }
        private static Loc[] cardinals = {
                new Loc(-1, 0),
                new Loc(0, 1),
                new Loc(1, 0),
                new Loc(0, -1),
        };
        public int largestIsland(int[][] grid) {
            this.grid = grid;
            this.nr = grid.length;
            this.nc = grid[0].length;
            int sz = 0;
            var islSizes = new HashMap<Integer, Integer>();
            for (int ir = 0; ir < nr; ir++) {
                for (int ic = 0; ic < nc; ic++) {
                    if (grid[ir][ic] <= 0) continue;
                    sz = dfs(new Loc(ir, ic));
                    islSizes.put(islandId, sz);
                    islandId--;
                }
            }
            if (islSizes.isEmpty()) return 1;
            if (islSizes.size() == 1) {
                sz = islSizes.get(islandId+1);
                if (sz == nr * nc) return sz;
                return sz+1;
            }
            int res = 1;
            for (int ir = 0; ir < nr; ir++) {
                for (int ic = 0; ic < nc; ic++) {
                    if (grid[ir][ic] != 0) continue;
                    var neighIds = new HashSet<Integer>();
                    int curr = 1;
                    for (var dir: cardinals) {
                        var cell = (new Loc(ir, ic)).next(dir);
                        if (!cell.isValid(nr, nc)) continue;
                        var islId = grid[cell.ir][cell.ic];
                        if (islId == 0) continue;
                        if (!neighIds.contains(islId)) {
                            neighIds.add(islId);
                            curr += islSizes.get(islId);
                        }
                    }
                    res = Math.max(res, curr);
                }
            }

            return res;
        }

        private int islandId = -1;
        private int dfs(Loc u) {
            var cell = grid[u.ir][u.ic];
            if (cell <= 0) return 0;
            int res = 1;
            grid[u.ir][u.ic] = islandId;
            for (var dir: cardinals) {
                var v = u.next(dir);
                if (!v.isValid(nr, nc)) continue;
                if (grid[v.ir][v.ic] <= 0) continue;
                res += dfs(v);
            }
            return res;
        }

        public static void main(String[] args) {
            int[][] grid = {
                    {1, 1}, {1, 1}
            };
            var res = (new Solution()).largestIsland(grid);
        }
    }
}
