package com.kandy.algorithm.week05.homework;

public class LC327区间和的个数 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.ans = 0;
        this.lower = lower;
        this.upper = upper;
        long s = 0;
        //计算前缀和
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            preSum[i + 1] = s;
        }
        mergeSort(preSum, 0, preSum.length - 1);
        return ans;
    }

    private void mergeSort(long[] nums, int left, int right) { // sort arr[l..r]
        if (left >= right) return;
        int mid = (left + right) >> 1; // (left + right) / 2
        //前一半排序
        mergeSort(nums, left, mid);
        //后一半排序
        mergeSort(nums, mid + 1, right);
        calculate(nums, left, mid, right);
        //合并左右两半（两个有序数组）
        merge(nums, left, mid, right);
    }

    //等价于preSum[j]−preSum[i] 属于[lower,upper]区间
    void calculate(long[] arr, int left, int mid, int right) {
        // 左边[left, mid]，右边[mid + 1, right]
        int l = mid + 1;
        int r = mid + 1;
        for (int i = left; i <= mid; i++) {
            //l 停在 >= lower
            while (l <= right && arr[l] - arr[i] < lower) {
                l++;
            }
            // r停在 > upper位置
            while (r <= right && arr[r] - arr[i] <= upper) {
                r++;
            }
            // r -l 即为符合条件的个数
            ans += r - l;
        }
    }

    //两个区间[left,mid] [mid+1,right]
    private void merge(long[] nums, int left, int mid, int right) {
        long[] temp = new long[right - left + 1]; //临时数组
        int i = left, j = mid + 1;
        for (int k = 0; k < temp.length; k++) { //合并两个有序数组 j已经到头或者 i j都没走到头，取较小者
            if (j > right || (i <= mid && nums[i] <= nums[j]))
                temp[k] = nums[i++];
            else
                temp[k] = nums[j++];
        }
        for (int k = 0; k < temp.length; k++) { //拷回原数组
            nums[left + k] = temp[k];
        }
    }

    int ans;
    int lower;
    int upper;
}
