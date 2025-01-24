package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {
    static class Solution {
        public boolean isValid(String s) {
            int[] arr = new int[s.length()];
            Map<Character, Integer> mp = new HashMap<>();
            mp.put('(', 1);
            mp.put('{', 2);
            mp.put('[', 3);
            mp.put(')', -1);
            mp.put('}', -2);
            mp.put(']', -3);
            var st = new Stack<Integer>();
            for (var ch: s.toCharArray()) {
                // if opening, add to stack
                var val = mp.get(ch);
                if (val > 0) {
                    st.push(val);
                    continue;
                }

                // for closing check if latestOpening matches.
                if (st.isEmpty()) return false;
                var latestOpening = st.pop();
                if (latestOpening != -val) return false;
            }
            return st.isEmpty();
        }

        public static void main(String[] args) {
            var s = "([{}])";
            System.out.println((new Solution()).isValid(s));
        }
    }
}
