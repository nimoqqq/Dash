package com.algorithm.sort;


import java.util.Arrays;

/**
 * @program: Dash
 * @ClassName: MergetSort
 * @description: 归并排序
 * @author: chuf
 * @create: 2021-11-18 08:12
 **/
public class MergetSort {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp = new int[arr.length];

        MergetSort mergetSort = new MergetSort();
        mergetSort.merge_sort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    public void merge_sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            merge_sort(arr, left, mid, temp);
            merge_sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }

    }

    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;

        //临时数组索引
        int t = 0;

        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }

        //将剩下的元素放入临时表中
        while (l <= mid) {
            temp[t++] = arr[l++];
        }

        while (r <= right) {
            temp[t++] = arr[r++];
        }

        //将临时数组复制回原数组
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i - left];
        }
    }
}
