package leetcode.daily;

// https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls/?envType=daily-question&envId=2025-02-07

import java.util.HashMap;
import java.util.HashSet;

public class NumDistinctColors {
    private static class Solution {
        public int[] queryResults(int limit, int[][] queries) {
            HashMap<Integer, Integer> col2idx = new HashMap<>();
            HashMap<Integer, Integer> idx2col = new HashMap<>();
            int[] res = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int idx = queries[i][0], col = queries[i][1];
                var currCol = idx2col.get(idx);
                if (currCol != null) {
                    // remove from current color.
                    col2idx.merge(currCol, -1, Integer::sum);
                    if (col2idx.get(currCol) == 0) {
                        col2idx.remove(currCol);
                    }
                }
                col2idx.merge(col, 1, Integer::sum);
                idx2col.put(idx, col);
                res[i] = col2idx.size();
            }
            return res;
        }

        public static void main(String[] args) {
            int[][] queries = {{1,4},{2,5},{1,3},{3,4}};
            (new Solution()).queryResults(4, queries);
        }
    }
}
