package leetcode.daily;

public class GridGame {
    public long gridGame(int[][] grid) {
        long row1Sum = 0;
        for (var num : grid[0]) row1Sum += num;
        long row2Sum = 0;
        var res = Long.MAX_VALUE;
        for (int i = 0; i < grid[0].length; i++) {
            row1Sum = row1Sum - grid[0][i];
            res = Math.min(Math.max(row1Sum, row2Sum), res);
            row2Sum = row2Sum + grid[1][i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {20,3,20,17,2,12,15,17,4,15},
                {20,10,13,14,15,5,2,3,14,3}
        };
        System.out.println((new GridGame().gridGame(arr)));
    }
}
