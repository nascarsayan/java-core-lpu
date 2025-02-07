package cses;

import java.util.Arrays;
import java.util.Scanner;

public class MissingCoinSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var n = sc.nextInt();
        long[] coins = new long[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        Arrays.sort(coins);
        long acc = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > acc+1) {
                System.out.println(acc+1);
                return;
            }
            acc += coins[i];
        }
        System.out.println(acc+1);
    }
}
