package leetcode;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        var res = strs[0];
        int st = 0, fl = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            fl = Math.min(fl, strs[i].length());
            for (int pos = 0; pos < fl; pos++ ) {
                if (strs[0].charAt(pos) != strs[i].charAt(pos)) {
                    fl = pos;
                    break;
                }
            }
        }
        return strs[0].substring(st, fl);
    }

    public static void main(String[] args) {
        String[] x = {
                "ab","a"
        };
        System.out.println((new LongestCommonPrefix()).longestCommonPrefix(x));
    }
}
