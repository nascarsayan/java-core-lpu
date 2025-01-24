package cses;

import java.util.Scanner;

public class ArrayDivision {

    static int arrayLen;
    static long subarrayCount;
    static long[] array;

    static boolean checkIfMaxSubarraySumPossible(long subarrSum) {
        long currSum = 0, currSubCnt = 0;
        for (var el: array) {
            if (currSum + el > subarrSum) {
                currSubCnt++;
                if (currSubCnt > subarrayCount) return false;
                currSum = el;
                continue;
            }
            currSum += el;
        }
        currSubCnt++;
        return currSubCnt <= subarrayCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arrayLen = sc.nextInt();
        subarrayCount = sc.nextInt();
        array = new long[arrayLen];
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }
        long st = array[0], fl = 0, mid, ans;
        for (var el: array) {
            st = Math.max(st, el);
            fl += el;
        }
        ans = fl;
        while (st <= fl) {
            mid = st + (fl - st) / 2;
            if (checkIfMaxSubarraySumPossible(mid)) {
                ans = mid;
                fl = mid-1;
                continue;
            }
            st = mid+1;
        }
        System.out.println(ans);
    }
}
