package com.algorithm.sort;


import java.util.Arrays;

//快速排序
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        QuickSort quickSort = new QuickSort();
        quickSort.quicksort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void quicksort(int arr[]) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 一趟快排，并返回交换后基数的下标
            int index = patition(arr, left, right);
            // 递归排序基数左边的数组
            sort(arr, left, index - 1);
            // 递归排序基数右边的数组
            sort(arr, index + 1, right);
        }
    }

    public int patition(int[] arr, int left, int right) {
        int p = arr[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (arr[j] >= p && i < j) {
                j--;
            }

            while (arr[i] <= p && i < j) {
                i++;
            }

            // 找到后交换两个数
            swap(arr, i, j);
        }
        swap(arr, left, i);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
