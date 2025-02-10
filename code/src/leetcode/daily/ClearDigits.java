package leetcode.daily;

import java.util.ArrayList;

public class ClearDigits {
    private static class Solution {
        public String clearDigits(String s) {
            ArrayList<Character> st = new ArrayList<>();
            for (var ch: s.toCharArray()) {
                if ('0' <= ch && ch <= '9') {
                    st.removeLast();
                    continue;
                }
                st.addLast(ch);
            }
            var res = new StringBuilder();
            for (var ch: st) res.append(ch);
            return res.toString();
        }
    }
}
