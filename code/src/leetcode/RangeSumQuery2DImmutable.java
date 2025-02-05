package leetcode;

// https://www.lintcode.com/problem/665/

public class RangeSumQuery2DImmutable {
    private static class NumMatrix {
        private int[][] pre;
        public NumMatrix(int[][] matrix) {
            int nr = matrix.length, nc;
            if (nr == 0 || matrix[0].length == 0) return;
            nc = matrix[0].length;
            pre = new int[nr+1][nc+1];
            for (int ir = 1; ir <= nr; ir++) {
                for (int ic = 1; ic <= nc; ic++) {
                    pre[ir][ic] = pre[ir][ic-1] + pre[ir-1][ic] - pre[ir-1][ic-1] + matrix[ir-1][ic-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return (pre[row2+1][col2+1] - pre[row2+1][col1] - pre[row1][col2+1] + pre[row1][col1]);
        }
    }
}
