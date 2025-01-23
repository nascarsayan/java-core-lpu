package collections;

public class Kadane {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSumEndingAtCurr = 0;
        int maxSum = Integer.MIN_VALUE;
        for (var val: arr) {
            maxSumEndingAtCurr += val;
            maxSum = Math.max(maxSum, maxSumEndingAtCurr);
            // Ditch negative backlog.
            if (maxSumEndingAtCurr < 0) maxSumEndingAtCurr = 0;
        }
        System.out.printf("max sum = %d\n", maxSum);
    }
}
