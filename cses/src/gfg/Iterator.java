package gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.geeksforgeeks.org/problems/java-iterator2702/1?page=2&category=Java&sortBy=submissions

public class Iterator {

    static ArrayList<Integer> javaIterator(int n, int k, int[] arr) {
        ArrayList<Integer> arr2 = new ArrayList<>();
        for (int e: arr) arr2.addLast(e);
        for (java.util.Iterator<Integer> it = arr2.iterator(); it.hasNext();) {
            if (it.next() < k) it.remove();
        }
        return arr2;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 9, 2, 11};
        System.out.println(javaIterator(5, 8, arr));
    }
}
