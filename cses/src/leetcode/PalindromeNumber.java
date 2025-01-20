package leetcode;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        var s1 = Integer.toString(x);
        for (int st=0, fl=s1.length()-1; st < fl; st++, fl--) {
           if (s1.charAt(st) != s1.charAt(fl)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        var x = 121;
        System.out.println((new PalindromeNumber()).isPalindrome(x));
    }
}
