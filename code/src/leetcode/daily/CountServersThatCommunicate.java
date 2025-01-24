package leetcode.daily;

// https://leetcode.com/problems/count-servers-that-communicate/description/?envType=daily-question&envId=2025-01-23

public class CountServersThatCommunicate {
    static class Solution {
        public int countServers(int[][] grid) {
            var nr = grid.length;
            var nc = grid[0].length;
            for (int ir = 0; ir < nr; ir++) {
                var cnt = 0;
                for (int ic = 0; ic < nc; ic++) {
                    if (grid[ir][ic] > 0) cnt++;
                }
                if (cnt < 2) continue;
                for (int ic = 0; ic < nc; ic++) {
                    if (grid[ir][ic] == 1) grid[ir][ic] = 2;
                }
            }

            for (int ic = 0; ic < nc; ic++) {
                var cnt = 0;
                for (int ir = 0; ir < nr; ir++) {
                    if (grid[ir][ic] > 0) cnt++;
                }
                if (cnt < 2) continue;
                for (int ir = 0; ir < nr; ir++) {
                    if (grid[ir][ic] == 1) grid[ir][ic] = 2;
                }
            }

            var cnt = 0;
            for (int ir = 0; ir < nr; ir++) {
                for (int ic = 0; ic < nc; ic++) {
                    if (grid[ir][ic] == 2) cnt++;
                }
            }
            return cnt;
        }

        public static void main(String[] args) {
            int[][] grid = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
            var res = (new Solution()).countServers(grid);
            System.out.println(res);
        }
    }
}
