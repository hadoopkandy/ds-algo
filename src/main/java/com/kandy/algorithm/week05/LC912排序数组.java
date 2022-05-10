package com.kandy.algorithm.week05;

//归并排序
public class LC912排序数组 {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) { // sort arr[l..r]
        if (left >= right) return;
        int mid = (left + right) >> 1; // (left + right) / 2
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; //定义临时数组
        int i = left, j = mid + 1;
        for (int k = 0; k < temp.length; k++) { // j已经到头 或者 i j 都没走到头，取较小者
            if (j > right || (i <= mid && nums[i] <= nums[j]))
                temp[k] = nums[i++];
            else
                temp[k] = nums[j++];
        }
        for (int k = 0; k < temp.length; k++) { //将temp数据的值，复制到原数组
            nums[left + k] = temp[k];
        }
    }
}
