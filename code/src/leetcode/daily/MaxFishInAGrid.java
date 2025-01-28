package leetcode.daily;

import java.util.ArrayDeque;

public class MaxFishInAGrid {
    static class Solution {
        static class Coord {
            int ir, ic;
            Coord(int ir, int ic) {
                this.ir = ir;
                this.ic = ic;
            }

            Coord next(Coord cardinal) {
                return new Coord(this.ir + cardinal.ir, this.ic+cardinal.ic);
            }
        }

        static boolean isValidCoord(Coord c,int nr, int nc) {
            if (!(0 <= c.ir && c.ir < nr)) return false;
            if (!(0 <= c.ic && c.ic < nc)) return false;
            return true;
        }
        public int findMaxFish(int[][] grid) {
            // reducing space complexity by storing visited info
            // using a negative value on the cell itself.
            var nr = grid.length;
            var nc = grid[0].length;
            int res = 0;
            Coord[] cardinals = {
                    new Coord(-1, 0),
                    new Coord(0, 1),
                    new Coord(1, 0),
                    new Coord(0, -1),
            };
            for (int ir = 0; ir < nr; ir++) {
                for (int ic = 0; ic < nc; ic++) {
                    // if cell is land or already visited previously, continue.
                    if (grid[ir][ic] <= 0) continue;
                    // do BFS
                    var frontier = new ArrayDeque<Coord>();
                    frontier.addLast(new Coord(ir, ic));
                    int collected = 0;
                    while (!frontier.isEmpty()) {
                        var u = frontier.pollFirst();
                        if (grid[u.ir][u.ic] <= 0) continue;
                        collected += grid[u.ir][u.ic];
                        grid[u.ir][u.ic] = -grid[u.ir][u.ic];
                        for (var c: cardinals) {
                            var v = u.next(c);
                            if (!isValidCoord(v, nr, nc)) continue;
                            if (grid[v.ir][v.ic] <= 0) continue;
                            frontier.addLast(v);
                        }
                    }
                    res = Math.max(res, collected);
                }
            }
            return res;
        }

        public static void main(String[] args) {
            int[][] grid = {
                    {0,2,1,0},
                    {4,0,0,3},
                    {1,0,0,4},
                    {0,3,2,0},
            };
            (new Solution()).findMaxFish(grid);
        }
    }
}
