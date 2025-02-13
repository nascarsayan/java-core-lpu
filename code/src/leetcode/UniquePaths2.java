package leetcode;

public class UniquePaths2 {
    private static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int nr = obstacleGrid.length, nc = obstacleGrid[0].length;
            int[][] res = new int[nr+1][nc+1];
            res[0][1] = 1;
            for (int ir = 1; ir <= nr; ir++) {
                for (int ic = 1; ic <= nc; ic++) {
                    if (obstacleGrid[ir-1][ic-1] == 1) continue;
                    res[ir][ic] = res[ir-1][ic] + res[ir][ic-1];
                }
            }
            return res[nr][nc];
        }

        public static void main(String[] args) {
            int[][] grid = {
                {0,0,0},{0,1,0},{0,0,0}
            };
            var res = (new Solution()).uniquePathsWithObstacles(grid);
            System.out.println(res);
        }
    }
}
