package cses;

import java.util.Arrays;
import java.util.Scanner;

public class FactoryMachines {

    static int machineCount;
    static long targetProductCount;
    static long[] machineDurations;

    static boolean isPossibleInDuration(long duration) {
        int produced = 0;
        for (var m: machineDurations) {
            produced += (int)(duration / m);
            if (produced >= targetProductCount) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        machineCount = sc.nextInt();
        targetProductCount = sc.nextLong();
        machineDurations = new long[machineCount];
        for (int i = 0; i < machineCount; i++) {
            machineDurations[i] = sc.nextLong();
        }

        Arrays.sort(machineDurations);
        long st = 0, fl = machineDurations[0] * targetProductCount, mid;
        long ans = fl;
        while (st <= fl) {
            mid = st + (fl - st) / 2;
            if (isPossibleInDuration(mid)) {
                ans = mid;
                fl = mid - 1;
                continue;
            }
            st = mid + 1;
        }

        System.out.println(ans);
    }
}
