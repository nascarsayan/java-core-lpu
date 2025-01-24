package cses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DistinctNumbers {
    public static void main(String[] args) {
        var valsArr = new ArrayList<Long>();
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            valsArr.add(sc.nextLong());
        }
        var vals = new HashSet<>(valsArr);
        System.out.println(vals.size());
    }
}
