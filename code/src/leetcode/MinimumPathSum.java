package leetcode;

import java.util.Arrays;

public class MinimumPathSum {
    private static class Solution {
        public int minPathSum(int[][] grid) {
            int inf = (int)1e7;
            int nr = grid.length, nc = grid[0].length;
            int[][] minSum = new int[nr+1][nc+1];
            Arrays.fill(minSum[0], inf);
            for (int ir = 0; ir <= nr; ir++) {
                minSum[ir][0] = inf;
            }
            // The only path into the grid is from (1, 0).
            // We can also enter through (0, 1).
            minSum[1][0] = 0;
            for (int ir = 1; ir <= nr; ir++) {
                for (int ic = 1; ic <= nc; ic++) {
                    // Minimum from top or left.
                    minSum[ir][ic] = Math.min(minSum[ir-1][ic], minSum[ir][ic-1]) + grid[ir-1][ic-1];
                }
            }
            return minSum[nr][nc];
        }
    }
}
