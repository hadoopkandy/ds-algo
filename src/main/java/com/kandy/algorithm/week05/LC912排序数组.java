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

    //堆排序 是对选择排序的优化，利用二叉堆高效地选出最大值
    public static void heapSort(int[] nums) {
        if (nums.length == 0) return;

        int length = nums.length;
        //通过堆化以后，数组的左半部分是较大的节点，左半部分满足大根堆性质，根节点最大
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapifyDown(nums, length, i);
        }

        //从后往前与堆顶交换
        for (int i = length - 1; i >= 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            heapifyDown(nums, i, 0);
        }
    }

    //自顶向下堆化 非递归实现
    public static void heapifyDown(int[] nums, int length, int i) {
        int child = i * 2 + 1;//要换的那个孩子
        while (child < length) {  // child未出界，说明i有合法的child，还不是叶子
            int otherChild = i * 2 + 2;//另一个孩子
            // 先比较两个孩子，谁大就继续跟i比较
            // child存较大的孩子
            if (otherChild < length && nums[otherChild] > nums[child])
                child = otherChild;
            // 让child跟i比较
            if (nums[child] > nums[i]) { // 大根堆
                int temp = nums[i];
                nums[i] = nums[child];
                nums[child] = temp;

                i = child;
                child = i * 2 + 1;
            } else break;
        }
    }

    //自顶向下堆化 递归实现
    public static void heapify(int[] nums, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2; //左右孩子
        int largest = i;

        //存在左孩子且左孩子比当前largest节点大
        if (left < length && nums[left] > nums[largest]) {
            largest = left;
        }
        //存在右孩子，且右孩子比当前largest节点大
        if (right < length && nums[right] > nums[largest]) {
            largest = right;
        }

        //当前节点和左右孩子相比不是较大的，就进行交换
        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            //继续往下堆化
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

    //快速排序
    public static void _quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);//将list数组进行一分为二
            _quickSort(list, low, middle - 1);//对低字表进行递归排序
            _quickSort(list, middle + 1, high);//对高字表进行递归排序
        }
    }

    public static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];//数组的第一个作为中轴
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high];//比中轴小的记录移到低端
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low];//比中轴大的记录移到高端
        }
        list[low] = tmp;//中轴记录到尾
        return low;//返回中轴的位置
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
