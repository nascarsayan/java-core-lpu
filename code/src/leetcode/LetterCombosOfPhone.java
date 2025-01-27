package leetcode;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombosOfPhone {
    static class Solution {
        String[] num2Chars = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> combos;
        String digits;
        void recurse(int digitIdx, StringBuilder accumulated) {
            if (digitIdx == digits.length()) {
                combos.add(accumulated.toString());
                return;
            }
            var digit = digits.charAt(digitIdx) - '0';
            for (var ch: num2Chars[digit].toCharArray()) {
                // take the element.
                accumulated.append(ch);
                recurse(digitIdx+1, accumulated);
                // remove last element.
                accumulated.setLength(accumulated.length()-1);
            }
        }
        public List<String> letterCombinations(String digits) {
            combos = new ArrayList<>();
            // Handle empty string.
            if (digits.isEmpty()) return  combos;
            this.digits = digits;
            recurse(0, new StringBuilder());
            return combos;
        }
    }
}
