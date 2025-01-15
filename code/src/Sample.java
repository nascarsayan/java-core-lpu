import java.util.HashMap;
import java.util.Scanner;

public class Sample {
    // create a static instance of DP map for memoization
    static HashMap<Integer, Integer> dp = new HashMap<>();
    static HashMap<Integer, Integer> testMxSize = new HashMap<>();

    static void fiboIterative(int count) {
        int f1 = 0, f2 = 1;
        for (int i = 0; i < count - 1; i++) {
            int temp = f1 + f2;
            f1 = f2;
            f2 = temp;
            System.out.println(f2);
        }
    }

    static int fiboRecursive(int count) {
        if (count == 0) return 0;
        if (count == 1) return 1;
        return fiboRecursive(count-1) + fiboRecursive(count-2);
    }

    static int fiboDP(int count) {
        if (count == 0) return 0;
        if (count == 1) return 1;
        // TODO: save and memoize the values of previously computed fiboDP
        if (dp.containsKey(count)) return dp.get(count);
        var k = fiboDP(count-1) + fiboDP(count-2);
        dp.put(count, k);
        return k;
    }

    static void validateMapMaxSize() {
        for (int i = 1; i <= 99999999; i++) {
            testMxSize.put(i, i*2);
        }
        System.out.println(testMxSize.get(99999999));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        fiboIterative(n);
//        System.out.println("recursive is: " + fiboRecursive(n));
//        System.out.println("dp is: " + fiboDP(n));
        validateMapMaxSize();
    }
}