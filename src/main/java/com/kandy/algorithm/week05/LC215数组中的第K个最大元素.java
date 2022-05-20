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
    public int quickSort(int[] nums, int l, int r, int index) {
        if (l >= r) return nums[l];
        int pivot = partition(nums, l, r);
        //左边够去左边找
        if (index <= pivot) return quickSort(nums, l, pivot, index);
        else return quickSort(nums, pivot + 1, r, index); //否则去右边找
    }

    int partition(int[] nums, int l, int r) {
        int pivot = l + (int) (Math.random() * (r - l + 1));
        int pivotVal = nums[pivot];

        while (l <= r) {
            while (nums[l] < pivotVal) l++;
            while (nums[r] > pivotVal) r--;
            if (l == r) break;
            if (l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
        return r;
    }
}
