package com.algorithm.sort;


/**
 * @program: Dash
 * @ClassName: MergetSort
 * @description: 归并排序
 * @author: chuf
 * @create: 2021-11-18 08:12
 **/
public class MergetSort {



    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;

        //临时数组索引
        int t = 0;

        while (l <= mid && r <= right) {
            if (arr[l++] < arr[r++]) {
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
        for (int i = left; i < right; i++) {
            arr[i] = temp[i - left];
        }
    }
}
