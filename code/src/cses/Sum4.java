package cses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Sum4 {
   static class IdxPair {
       int idx1, idx2;
       IdxPair(int idx1, int idx2) {
           this.idx1 = idx1;
           this.idx2 = idx2;
       }
   }
    public List<Integer> fourSum(int[] nums, int target) {
        HashMap<Integer, IdxPair> pairSumsTillNow = new HashMap<>();
        if (nums.length < 4) return null;
        pairSumsTillNow.put(nums[0]+nums[1], new IdxPair(0, 1));
        for (int p3 = 2; p3 < nums.length-1; p3++) {
            for (int p4 = p3+1; p4 < nums.length; p4++) {
                var rem = target - nums[p3] - nums[p4];
                if (pairSumsTillNow.containsKey(rem)) {
                    var p = pairSumsTillNow.get(rem);
                    return new ArrayList<>(List.of(p.idx1, p.idx2, p3, p4));
                }
            }
            for (int p1 = 0; p1 < p3; p1++) {
                pairSumsTillNow.put(nums[p1]+nums[p3], new IdxPair(p1, p3));
            }
        }
        return null;
    }

   public static void main(String[] args) {
       int n, target;
       Scanner sc = new Scanner(System.in);
       n = sc.nextInt();
       target = sc.nextInt();
       int[] nums = new int[n];
       for (int i = 0; i < n; i++) {
           nums[i] = sc.nextInt();
       }
       var obj = new Sum4();
       var res = obj.fourSum(nums, target);
       if (res == null) {
           System.out.println("IMPOSSIBLE");
           return;
       }
       for (var idx: res) {
           System.out.print(idx+1 + " ");
       }
       System.out.println();
   }
}
