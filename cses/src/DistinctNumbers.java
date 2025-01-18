import java.util.HashSet;
import java.util.Scanner;

public class DistinctNumbers {
    public static void main(String[] args) {
        var vals = new HashSet<Long>();
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            vals.add(sc.nextLong());
        }
        System.out.println(vals.size());

    }
}
