package com.kandy.algorithm.week04;

import java.util.Arrays;

/**
 * 常见的4种二分查找的变体问题
 * 变体一：查找第一个值等于给定值的元素
 * 变体二：查找最后一个值等于给定值的元素
 * 变体三：查找第一个大于等于给定值的元素 （后继）
 * 变体四：查找最后一个小于等于给定值的元素 （前驱）
 *
 * 二分代码 "三步走"
 * 1.写出二分的条件 （一般是一个不等式）例如：upper_bound >val里最小的
 * 2.把条件放到if(...)里，并确定满足条件时要小的(right=mid) 还是要大的(left=mid)
 * 3.另一半放到else里，(left = mid+1或right=mid-1) ，如果是后者求mid时补+1
 * 如果题目有无解的情况，上界增加1，下界减小1，用于标识无解
 */
public class LC34在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        //开始位置：第一个>=target的数 后继型
        //结束位置：最后一个<=target的数 前驱型
        int[] ans = new int[2];
        int left = 0;
        int right = nums.length; //表示无解
        //循环终止与left = right
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid; //在>=里面要小的
            } else {
                left = mid + 1;
            }
        }
        ans[0] = right;

        //前继型
        left = -1; //表示无解
        right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] <= target) {
                left = mid; //在<=里面要大的
            } else {
                right = mid - 1;
            }
        }
        ans[1] = right;

        if (ans[0] > ans[1]) return new int[]{-1, -1};
        //符合ans[0]<=ans[1]才是答案
        return ans;
    }

    //变体一：查找第一个值等于给定值的元素
    public int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;//如果前一个元素也等于value，那么答案位于[low,mid-1]区间
            }
        }
        return -1;
    }

    //变体二：查找最后一个值等于给定值的元素
    public int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;//如果后一个元素也等于value，那那么答案位于[mid+1,high]区间
            }
        }
        return -1;
    }

    //变体三：查找第一个大于等于给定值的元素
    public int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1; //如果前一个元素也大于等于value，那么答案位于[low,mid-1]区间
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //变体四：查找最后一个小于等于给定值的元素
    public int bsearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;//如果后一个元素也小于等于value，那么答案位于[mid+1,high]区间
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC34在排序数组中查找元素的第一个和最后一个位置 lc = new LC34在排序数组中查找元素的第一个和最后一个位置();
        int[] nums  = new int[]{2,2};
        System.out.println(Arrays.toString(lc.searchRange(nums,3)));
    }
}
