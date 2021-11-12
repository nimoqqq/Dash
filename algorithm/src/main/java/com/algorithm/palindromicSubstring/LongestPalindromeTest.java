package com.algorithm.palindromicSubstring;

public class LongestPalindromeTest {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();

        String s = "acvfabcddcba";
        String longestPalindromeSubString = solution2.longestPalindrome(s);
        System.out.println(longestPalindromeSubString);
    }
}
