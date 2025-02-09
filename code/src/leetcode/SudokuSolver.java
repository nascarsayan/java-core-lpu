package leetcode;

public class SudokuSolver {
    private static class Solution {
        boolean [][] rows, cols, boxes;
        char[][] board;

        int getBoxIdx(int i, int j) {
            return 3 * (i / 3) + (j / 3);
        }

        int[] getNextIdx(int i, int j) {
            var newIdxSum = (i * 9 + j + 1);
            int[] res = new int[2];
            res[0] = newIdxSum / 9;
            res[1] = newIdxSum % 9;
            return res;
        }

        boolean recurse(int i, int j) {
            if (i == 9) return true;
            var next = getNextIdx(i, j);
            int i2 = next[0], j2 = next[1];
            if (board[i][j] != '.') {
                return recurse(i2, j2);
            }
            boolean done = false;
            for (int num = 0; num < 9; num++) {
                if (rows[i][num]) continue;
                if (cols[j][num]) continue;
                if (boxes[getBoxIdx(i, j)][num]) continue;
                // set the board, and recurse.
                board[i][j] = (char)('1' + num);
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[getBoxIdx(i, j)][num] = true;
                done = recurse(i2, j2);
                if (done) return true;
                // reset the board for the next move.
                board[i][j] = '.';
                rows[i][num] = false;
                cols[j][num] = false;
                boxes[getBoxIdx(i, j)][num] = false;
            }
            return false;
        }

        public void solveSudoku(char[][] board) {
            rows = new boolean[9][9];
            cols = new boolean[9][9];
            boxes = new boolean[9][9];
            this.board = board;
            // fill the row, col, box
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;
                    int num = board[i][j] - '1';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[getBoxIdx(i, j)][num] = true;
                }
            }
            recurse(0, 0);
        }

        public static void main(String[] args) {
            char[][] board = {
                    {'.', '.', '.', '.', '.', '9', '.', '4', '.'},
                    {'.', '.', '.', '1', '.', '.', '8', '.', '.'},
                    {'6', '.', '7', '.', '3', '.', '.', '.', '.'},
                    {'.', '5', '6', '3', '.', '.', '.', '8', '.'},
                    {'.', '.', '3', '.', '7', '.', '.', '5', '.'},
                    {'7', '.', '.', '2', '.', '.', '1', '.', '.'},
                    {'.', '9', '.', '.', '.', '.', '7', '.', '.'},
                    {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '6', '.', '.', '.', '1', '4'}
            };

            (new Solution()).solveSudoku(board);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
