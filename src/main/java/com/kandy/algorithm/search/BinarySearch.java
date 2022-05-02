package com.kandy.algorithm.search;


/**
 * 二分查找算法（递归与非递归两种方式）
 */
public class BinarySearch {

    /**
     * 该方法用作描述中间数,其中以"*"标注的元素表示每一次查找过程中的中间元素
     */
    public static void mark(int[] array, int middle) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i == middle) {
                System.out.print("*");
            }
            System.out.print("   ");
        }
        System.out.println();
    }

    /**
     * 非递归二分查找算法
     * 获取一个数值,然后和数组中的元素根据二分查找法查找到该数值的下标
     */
    public static int BinaryIndexOf(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int middle;
        while (low <= high)//当low"指针"和high不重复的时候
        {
            //计算中间元素的下标,也可以用“int middle=low+((high-low)>>1);"low+ 最高位置减去最低位置,右移一位,相当于除2
            middle = (low + high) / 2;
            // mark(array,middle);
            //与最中间的数字进行判断,是否相等,相等的话就返回对应的数组下标
            if (array[middle] == value) {
                return middle;
            }
            ////如果小于的话则移动最高层的"指针"
            else if (array[middle] > value) {
                high = middle - 1;
            }
            //移动最低的"指针"
            else {
                low = middle + 1;
            }
        }
        return -1;//如果没有找到相应的元素,就返回-1
    }

    /**
     * 递归方法实现二分查找法.
     */
    public static int BinaryIndexOf(int array[], int low, int high, int key) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (key == array[mid])
                return mid;
            else if (key < array[mid])
                //移动low和high
                return BinaryIndexOf(array, low, mid - 1, key);
            else if (key > array[mid])
                return BinaryIndexOf(array, mid + 1, high, key);
        }
        return -1;
    }

    /**
     * 定义一个int类型的数组,并把该数组传递给二分查询算法方法  BinaryIndexOf()
     */
    public static void main(String[] args) {
        int[] array = {22, 34, 55, 77, 89, 93, 99, 102, 120, 140};
        System.out.println("77 index=" + BinaryIndexOf(array, 77));
        System.out.println("77 index=" + BinaryIndexOf(array, 0, array.length - 1, 77));
        System.out.println("34 index=" + BinaryIndexOf(array, 34));
        System.out.println("99 index=" + BinaryIndexOf(array, 99));
    }
}

