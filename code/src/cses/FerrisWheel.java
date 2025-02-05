package cses;

import java.util.*;

public class FerrisWheel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input values for n and mx
        int n = scanner.nextInt();
        int mx = scanner.nextInt();

        // Read the array of weights and sort it
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        Arrays.sort(w);

        // Initialize pointers and result counter
        int l = 0, r = n - 1;
        int res = 0;

        // Process the array using two-pointer technique
        while (l < r) {
            res++;
            if (w[l] + w[r] > mx) {
                r--;
                continue;
            }
            l++;
            r--;
        }

        // If the pointers meet, increment the result
        if (l == r) {
            res++;
        }

        // Output the result
        System.out.println(res);
    }
}
