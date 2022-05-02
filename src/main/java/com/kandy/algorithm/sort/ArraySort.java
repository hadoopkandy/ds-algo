package com.kandy.algorithm.sort;

/**
 * 极客时间
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
    public static int[] sort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sort(a, low, mid);
            sort(a, mid + 1, high);
            //左右归并
            merge(a, low, mid, high);
        }
        return a;
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }

    //快速排序
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

    public static void _quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);//将list数组进行一分为二
            _quickSort(list, low, middle - 1);//对低字表进行递归排序
            _quickSort(list, middle + 1, high);//对高字表进行递归排序
        }
    }
}
