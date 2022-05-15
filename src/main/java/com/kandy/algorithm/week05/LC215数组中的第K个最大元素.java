package com.kandy.algorithm.week05;

/**
 * 排序后下标是n-k的值是多少
 */
public class LC215数组中的第K个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    // 求排序以后下标在index这里的数是多少
    //时间复杂度 n + n/2 + n/4 + n/8 + ... +1 < 2n
    public int quickSort(int[] arr, int l, int r, int index) {
        if (l >= r) return arr[l];
        int pivot = partition(arr, l, r);
        //左边够去左边找
        if (index <= pivot) return quickSort(arr, l, pivot, index);
        else return quickSort(arr, pivot + 1, r, index); //否则去右边找
    }

    int partition(int[] a, int l, int r) {
        int pivot = l + (int) (Math.random() * (r - l + 1));
        int pivotVal = a[pivot];

        while (l <= r) {
            while (a[l] < pivotVal) l++;
            while (a[r] > pivotVal) r--;
            if (l == r) break;
            if (l < r) {
                int temp = a[l];
                a[l] = a[r];
                a[r] = temp;
                l++;
                r--;
            }
        }
        return r;
    }
}
