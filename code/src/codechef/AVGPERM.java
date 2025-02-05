package codechef;

import java.util.Scanner;

public class AVGPERM {
    private static class Codechef
    {
        public static void main (String[] args) throws java.lang.Exception
        {
            Scanner sc = new Scanner(System.in);
            var t = sc.nextInt();
            for (int i = 0; i < t; i++) {
                var n = sc.nextInt();
                int[] arr = new int[n];
                int l=0, r=n-1;
                for (int j = n; j >= 1; j--) {
                    // parity - 0 is left ptr, 1 is right ptr
                    if (j % 2 == 0) {
                        arr[l] = j;
                        l++;
                        continue;
                    }
                    arr[r] = j;
                    r--;
                }
                for (int j = 0; j < n; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
            }
        }
    }
}
