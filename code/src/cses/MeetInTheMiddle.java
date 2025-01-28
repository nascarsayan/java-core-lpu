package cses;

// https://cses.fi/problemset/task/1628

import java.util.HashMap;
import java.util.Scanner;

public class MeetInTheMiddle {

    static Integer[] nums;
    static int target;
    static void computeSums(HashMap<Integer, Integer> freq, int st, int fl) {
        var totalNums = fl-st;
        int maxMask = (1 << totalNums) - 1;
        for (int mask = 0; mask <= maxMask; mask++) {
            int sumCurr = 0;
            for (int i = 0; i < totalNums; i++) {
                var idx = st+i;
                var curr = (1 << i);
                if ((curr & mask) > 0) {
                    sumCurr += nums[idx];
                }
            }
            freq.merge(sumCurr, 1, Integer::sum);
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        target = sc.nextInt();
        nums = new Integer[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        HashMap<Integer, Integer> freq1 = new HashMap<>(), freq2 = new HashMap<>();

        int st1 = 0, fl1 = n/2;
        int st2 = fl1, fl2 = n;
        computeSums(freq1, st1, fl1);
        computeSums(freq2, st2, fl2);

        var result = 0;

        for (var sumFromPart1: freq1.keySet()) {
            var f1 = freq1.get(sumFromPart1);
            var rem = target-sumFromPart1;
            var f2 = freq2.getOrDefault(rem, 0);
            result += f1 * f2;
        }

        System.out.println(result);
    }
}
