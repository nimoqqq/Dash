package com.algorithm.palindromicSubstring;

/**
 * 最长回文子串 · Longest Palindromic Substring
 * 给出一个字符串（假设长度最长为1000），求出它的最长回文子串，你可以假定只有一个满足条件的最长回文串。
 * 输入:"abcdzdcab"
 * 输出:"cdzdc"
 * <p>
 * <p>
 * <p>
 * 解题思路：
 * for 循环遍历字符串的首尾，然后判段该字符串是否是回文
 */
public class Solution1 {

    public String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 0;
        String result = null;
        for (int start = 0; start < len; start++) {
            for (int end = start; end < len; end++) {
                if (end - start + 1 <= maxLen) {
                    continue;
                }

                if (isPalindrome(s, start, end)) {
                    maxLen = end - start + 1;
                    result = s.substring(start, end + 1);
                }
            }
        }
        return result;
    }


    private boolean isPalindrome(String s, int start, int end) {
        for (int i = 0; start + i < end - i; i++) {
            if (s.charAt(start + i) != s.charAt(end - i)) {
                return false;
            }
        }
        return true;
    }

}
