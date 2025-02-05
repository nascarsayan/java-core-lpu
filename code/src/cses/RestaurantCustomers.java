package cses;

import java.util.Arrays;
import java.util.Scanner;

public class RestaurantCustomers {
    static class Marker implements Comparable {
        int time, increment;
        Marker(int time, int increment) {
            this.time = time;
            this.increment = increment;
        }

        @Override
        public int compareTo(Object o) {
            if (! (o instanceof Marker)) return 1;
            return this.time - ((Marker) o).time;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var n = sc.nextInt();
        Marker[] markers = new Marker[2*n];
        int st, fl;
        for (int i = 0; i < n; i++) {
            st = sc.nextInt();
            fl = sc.nextInt();
            markers[2*i] = new Marker(st, 1);
            markers[2*i+1] = new Marker(fl, -1);
        }
        Arrays.sort(markers);
        int curr = 0, res = 0;
        for (int i = 0; i < 2*n; i++) {
            curr += markers[i].increment;
            res = Math.max(res, curr);
        }
        System.out.println(res);
    }
}
