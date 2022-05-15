package com.kandy.algorithm.week05;

import java.util.Arrays;

/**
 * 极客时间
 * Java实现八大排序算法
 * https://blog.csdn.net/weixin_30342209/article/details/96022153
 */
public class LC912排序数组 {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    //选择排序
    //每次从未排序数据中找最小值，放到已排序序列的末尾
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int k = i;//默认i这个位置是最小的
            for (int j = i; j < nums.length; j++) {
                //如果找到j位置更小，就更新k
                if (nums[k] > nums[j]) {
                    k = j;
                }
            }
            //如果i 不是这一轮循环最小的，就交换i 与 最小的k
            if (i != k) {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }
    }

    //堆排序 是对选择排序的优化，利用二叉堆高效地选出最小值
    public static void heapSort(int[] nums) {
        if (nums.length == 0) return;

        int length = nums.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(nums, length, i);
        }

        for (int i = length - 1; i >= 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapify(nums, i, 0);
        }
    }

    public static void heapify(int[] nums, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;

        if (left < length && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < length && nums[right] > nums[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            heapify(nums, length, largest);
        }
    }

    //插入排序
    //从前到后依次考虑每个未排序数据，在已排序序列中找到合适位置插入
    public static void insertSort(int[] nums) {
        int n = nums.length;
        int target;
        //从数组的第二个元素开始循环将数组中的元素插入
        for (int i = 1; i < n; i++) {
            int j = i;
            target = nums[i];
            while (j > 0 && target < nums[j - 1]) {
                //如果要播入的元素小于第j-1个元素,就将第j-1个元素向后移动
                nums[j] = nums[j - 1];
                j--;
            }
            //直到要插入的元素不小于第j-1个元素,将target插入到数组中
            nums[j] = target;
        }
    }

    //冒泡排序
    //不断循环扫描，每次查看相邻的元素，如果逆序，则交换
    public static void bubbleSort(int[] nums) {
        //外层循环控制比较的次数
        for (int i = 0; i < nums.length - 1; i++) {
            //内层循环控制到达位置
            for (int j = 0; j < nums.length; j++) {
                //前面的元素比后面大就交换
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    //快速排序 基于分治的算法
    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        //从数组中选取中轴pivot
        int pivot = partition(nums, l, r);
        //将小元素放在pivot左边，大元素放在右边
        quickSort(nums, l, pivot);
        quickSort(nums, pivot + 1, r);
    }

    public static int partition(int[] nums, int l, int r) {
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

    //归并排序 基于分治的算法 O(nlogn) 合并时间复杂度O(n) logn层
    private void mergeSort(int[] nums, int left, int right) { // sort arr[l..r]
        if (left >= right) return;
        int mid = (left + right) >> 1; // (left + right) / 2
        //前一半排序
        mergeSort(nums, left, mid);
        //后一半排序
        mergeSort(nums, mid + 1, right);
        //合并左右两半（两个有序数组）
        merge(nums, left, mid, right);
    }

    //两个区间[left,mid] [mid+1,right]
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; //临时数组
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
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }

    public static void main(String[] args) {
        int nums[] = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
