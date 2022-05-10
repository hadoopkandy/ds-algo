package com.kandy.algorithm.week05;

import java.util.Arrays;

/**
 * 极客时间
 * Java实现八大排序算法
 * https://blog.csdn.net/weixin_30342209/article/details/96022153
 */
public class ArraySort {

    //插入排序
    public static void insertSort(int[] a) {
        int n = a.length;
        int target;
        //从数组的第二个元素开始循环将数组中的元素插入
        for (int i = 1; i < n; i++) {
            int j = i;
            target = a[i];
            while (j > 0 && target < a[j - 1]) {
                //如果要播入的元素小于第j-1个元素,就将第j-1个元素向后移动
                a[j] = a[j - 1];
                j--;
            }
            //直到要插入的元素不小于第j-1个元素,将target插入到数组中
            a[j] = target;
        }
    }

    //选择排序
    public static void Sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i; j < array.length; j++) {
                if (array[k] > array[j]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
    }


    //归并排序
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

    public static int partition(int[] a, int l, int r) {
//        int pivot = l + (int) (Math.random() * (r - l + 1));
        int pivot = r;

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

    //快速排序
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = partition(arr, l, r);
        quickSort(arr, l, pivot);
        quickSort(arr, pivot + 1, r);
    }

    public static void main(String[] args) {
        int nums[] = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};

        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    //堆排序
    public static void heapSort(int[] a) {

        for (int i = a.length - 1; i > 0; i--) {
            max_heapify(a, i);

            //堆顶元素(第一个元素)与Kn交换
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
        }
    }

    /***
     *  将数组堆化
     *  i = 第一个非叶子节点。
     *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
     *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
     * @param a
     * @param n
     */
    public static void max_heapify(int[] a, int n) {
        int child;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            //左子节点位置
            child = 2 * i + 1;
            //右子节点存在且大于左子节点，child变成右子节点
            if (child != n && a[child] < a[child + 1]) {
                child++;
            }
            //交换父节点与左右子节点中的最大值
            if (a[i] < a[child]) {
                int temp = a[i];
                a[i] = a[child];
                a[child] = temp;
            }
        }
    }
}
