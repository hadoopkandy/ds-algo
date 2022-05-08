package com.kandy.algorithm.week04.homework;

/**
 * 两次二分查找 列查找行，行查找元素
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

}
