package com.kandy.algorithm.week05;

/**
 * 在一个数组中统计满足特定大小关系的pair数量，可以考虑基于归并排序求解
 */
public class L06_C493翻转对 {
    public int reversePairs(int[] nums) {
        ans = 0;
        mergeSort(nums, 0, nums.length - 1);
        return ans;
    }

    void mergeSort(int[] arr, int l, int r) {  // sort arr[l..r]
        if (l >= r) return;
        int mid = (l + r) >> 1; // (l + r) / 2
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        calculate(arr, l, mid, r);
        merge(arr, l, mid, r);
    }


    //左边：1 3 4 7  右边：0 1 1 2
    //左边取一个i,右边取一个j,统计nums[i] > 2*nums[j]数量
    void calculate(int[] arr, int left, int mid, int right) {
        // 左边[left, mid]，右边[mid + 1, right]
        int j = mid;
        for (int i = left; i <= mid; i++) {
            // j停在条件满足
            while (j < right && arr[i] > 2l * arr[j + 1]) j++;
            ans += j - mid; // j - mid = j -(m +1) + 1
        }
    }
    void calculate2(int[] arr, int left, int mid, int right) {
        // 左边[left, mid]，右边[mid + 1, right]
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            // j停在条件不满足
            while (j <= right && arr[i] > 2l * arr[j]) j++;
            ans += j - mid -1; // j- 1 -(mid + 1) + 1 = j - mid - 1
        }
    }

    void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];  // 临时数组
        int i = left, j = mid + 1;

        for (int k = 0; k < temp.length; k++) {  // 合并两个有序数组
            if (j > right || (i <= mid && arr[i] <= arr[j]))
                temp[k] = arr[i++];
            else
                temp[k] = arr[j++];
        }

        for (int k = 0; k < temp.length; k++) {  // 拷回原数组
            arr[left + k] = temp[k];
        }
    }

    int ans;
}
