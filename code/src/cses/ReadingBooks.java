package cses;

import java.util.Scanner;

public class ReadingBooks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var n = sc.nextInt();
        long sum = 0, largest = 0;
        for (int i = 0; i < n; i++) {
            var val = sc.nextLong();
            sum += val;
            largest = Math.max(largest, val);
        }
        var sumOfRest = sum - largest;
        var totalTime = (largest > sumOfRest) ? 2 * largest : sum;
        System.out.println(totalTime);
    }
}
