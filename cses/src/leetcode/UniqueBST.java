package leetcode;

import java.util.HashMap;
import java.util.Map;

public class UniqueBST {
    static Map<Integer, Integer> dp = new HashMap<>();
    public int numTrees(int n) {
        if (n < 0) return 0;
        if (n <= 1) return 1;
        if (n == 2) return 2;
        if (dp.containsKey(n)) return dp.get(n);
        int res = 0;
        for (int mid = 1; mid <= n; mid++) {
            var l = numTrees(mid-1);
            var r = numTrees(n-mid);
            res += l * r;
            // System.out.printf("mid = %d left = %d, right = %d\n", mid, l, r);
        }
        dp.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        var x = (new UniqueBST()).numTrees(3);
        System.out.println(x);
    }
}
