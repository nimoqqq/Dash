package com.algorithm.strstrll;

/**
 * @program: Dash
 * @ClassName: Solution
 * @description:
 * @author: chuf
 * @create: 2021-11-13 23:38
 * <p>
 * 采用字符串哈希，字符串哈希时需要将字符串映射为数字，hash_target = (hash_target * 33 + target.charAt(i) - 'a') % mod;此处哈希函数，提供了字符串转化数字的关系式。
 * 对于需要匹配的子串对应一个值，然后再遍历主串，当前位置为i，则用i的哈希值减去i-m部分的哈希值，求出中间m个长度的子串的哈希值，如果与要匹配串相同，由于哈希本身不安全，需要截取出来m长度的子串再进行匹配，完全相同即可。
 * 注意负数取模时，需要通过+mod，将负数变为正数。
 * kmp是线性的字符串匹配算法，同样可以实现。
 **/
public class Solution {
    private static final Integer BASE = 1000000;

    public int strStr2(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }

        int m = target.length();
        if (m == 0) {
            return -1;
        }

        int power = 1;
        // 31 ^ m
        for (int i = 0; i < m; i++) {
            power = (power * 31) % BASE;
        }

        //先计算 target的 hash 值
        int targetCode = 0;
        for (int i = 0; i < m; i++) {
            targetCode = (targetCode * 31 + target.charAt(i)) % BASE;
        }

        //计算
        int sourceCode = 0;
        for (int i = 0; i < source.length(); i++) {
            sourceCode = (sourceCode * 31 + source.charAt(i)) % BASE;

            if (i < m - 1) {
                continue;
            }
            if (i >= m) {
                sourceCode = (sourceCode - power * source.charAt(i - m)) % BASE;
                if (sourceCode < 0) {
                    sourceCode += BASE;
                }
                //如返回的 hash 相同，比较字符是否相同
                if (sourceCode == targetCode) {
                    if (target.equals(source.substring(i - m + 1, i + 1))) {
                        return i - m + 1;
                    }
                }
            }
        }
        return -1;
    }
}
