package leetcode.daily;

public class RemoveAllOccurenceOfSubstring {
    static class Solution {

        private String getNextTrimmedFromZ(String full, String part) {
            String s = part + "#" + full;
            int x = 0, y = 0, n = s.length();
            int[] Z = new int[n];
            int fullBeginIdx = part.length() + 1;
            for (int i = 1; i < n; i++) {
                Z[i] = 0;
                if (y >= i) Z[i] = Math.min(y-i+1, Z[i-x]);
                while (i+Z[i] < n && s.charAt(Z[i]) == s.charAt(i+Z[i])) {
                    Z[i]++;
                }
                if (i+Z[i]-1 > y) {
                    x = i;
                    y = i+Z[i]-1;
                }
                if (y > 0 && y-x+1 == part.length()) {
                    return full.substring(0, x-fullBeginIdx) + full.substring(y+1-fullBeginIdx);
                }
            }
            return null;
        }

        public String removeOccurrences(String s, String part) {
            while (true) {
                var next = getNextTrimmedFromZ(s, part);
                if (next == null) return s;
                s = next;
            }
        }

        public static void main(String[] args) {
            var res = (new Solution()).removeOccurrences("wwwwwwwwwwwwwwwwwwwwwvwwwwswxwwwwsdwxweeohapwwzwuwajrnogb", "w");
            System.out.println(res);
        }
    }
}
