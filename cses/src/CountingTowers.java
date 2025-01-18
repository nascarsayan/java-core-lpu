import java.util.HashMap;
import java.util.Scanner;

public class CountingTowers {

    static HashMap<Long, TotalWays> dp = new HashMap<>();
    static long mod = 1000000007;

    // TotalWays is the count of ttotal number of ways we can
    // constuct a tower of height n.
    // width 1 ends with length 1,1. 🟩🟥
    // width 2 ends with 2. 🟩🟩
    static class TotalWays {
        long width1;
        long width2;

        TotalWays(long width1, long width2) {
            this.width1 = width1;
            this.width2 = width2;
        }

        @Override
        public String toString() {
            return "width1: " + width1 + " width2: " + width2;
        }
    }

    static TotalWays solve(long n) {
        if (n==1) return new TotalWays(1, 1);

        // memoize
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        // Ways in which we can solve n-1 height tower.
        var prev = solve(n-1);

        /*
          🟥🟥  🟥🟦      🟥🟦
          🟥🟥  🟥🟦      🟪🟦
        */
        var withWidth1 = (prev.width1 * 4 + prev.width2) % mod;

        /*
          🟥🟦      🟥🟦  🟥🟥  🟥🟦  🟥🟥
          🟥🟪      🟩🟪  🟩🟪  🟩🟩  🟩🟩
        */
        var withWidth2 = (prev.width1 + prev.width2 * 2) % mod;
        var res = new TotalWays(withWidth1, withWidth2);
        dp.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var testcases = sc.nextLong();
        for (long i = 0; i < testcases; i++) {
            var height = sc.nextLong();
            var res = solve(height);
            System.out.println((res.width1 + res.width2) % mod);
        }
    }
}
