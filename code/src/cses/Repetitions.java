package cses;

import java.util.Scanner;

public class Repetitions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var s = sc.next();
        s += "X";
        int res = 1, curr = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i-1)) {
                res = Math.max(res, curr);
                curr = 1;
                continue;
            }
            curr++;
        }
        System.out.println(res);
    }
}