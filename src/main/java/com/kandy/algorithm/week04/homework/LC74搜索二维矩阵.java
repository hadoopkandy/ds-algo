package com.kandy.algorithm.week04.homework;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix
 * 思路一：两次二分查找 列查找行，行查找元素
 * 思路二：将二维数组映射成一维数组进行标准二分查找
 */
public class LC74搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearch(matrix, target);
        if (rowIndex == -1) {
            return false;
        }
        int columnIndex = binarySearch(matrix[rowIndex], target);
        if (columnIndex == -1) {
            return false;
        }
        return true;
    }

    //对第一列进行二分查找，找到最后一个<=目标值的元素,返回改元素所在的行index
    private int binarySearch(int[][] matrix, int target) {
        //前继型
        int left = -1; //表示无解
        int right = matrix.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (matrix[mid][0] <= target) {
                left = mid; //在<=里面要大的
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //解法2：将二维数组映射成一维数组进行标准二分查找
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 3*4的二维数组，下标为6的元素，对应二维数组matrix[1][2]的元素
            int midValue = matrix[mid / n][mid % n];
            if (midValue == target) return true;
            if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

}
