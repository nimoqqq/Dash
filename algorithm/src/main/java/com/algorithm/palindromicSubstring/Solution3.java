package com.algorithm.palindromicSubstring;

/**
 * 由回文串正序和反序的性质相同，可以得出一个性质，如果一个字符串，其中心不是回文串，那么它一定不是个回文串。
 * 如果去掉头和尾，它依然还是一个回文串。在头和尾加上同一个字符也是一个回文串。
 *
 * 由此可以得出判断一个区间是否是回文串，可以由更小的区间得到，并且不受包含这个区间的大区间影响，所以满足无后效性且是最有子结构，可以用动态规划求解。
 */
public class Solution3 {

}