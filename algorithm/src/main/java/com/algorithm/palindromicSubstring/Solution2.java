package com.algorithm.palindromicSubstring;

/**
 * 由回文串正序和反序的性质相同，可以得出一个性质，如果一个字符串，其中心不是回文串，那么它一定不是个回文串。
 * 所以我们每次从中心开始，向两边延展首尾，判断是否是回文串。
 */
public class Solution2 {

    public String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 0;
        String result = null;

        for (int center = 0; center < len; center++) {
            //奇数长度
            for (int start = center, end = center; valid(start, end, len); start--, end++) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                int newLen = end - start + 1;
                if (newLen > maxLen) {
                    maxLen = newLen;
                    result = s.substring(start, end + 1);
                }
            }

            //偶数长度
            for (int start = center, end = center + 1; valid(start, end, len); start--, end++) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                int newLen = end - start + 1;
                if (newLen > maxLen) {
                    maxLen = newLen;
                    result = s.substring(start, end + 1);
                }
            }
        }

        return result;
    }

    private boolean valid(int start, int end, int len) {
        return start > -1 && end < len;
    }
}
