package com.kandy.algorithm.week04;

/**
 * 三分查找
 * 以求单峰函数f的极大值为例，可定义域[l,r]上取任意两点lmid,rmid
 * 若f(lmid) <= f(rmid)，则函数必然在lmid处单调递增，极值在[lmid,r]上
 * 若f(lmid) > f(rmid)，则函数必然在rmid处单调递减，极值在[l,rmid]上
 */
public class LC162寻找峰值 {
    //极大值
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int lmid = (left + right) / 2;
            int rmid = lmid + 1;
            if (nums[lmid] <= nums[rmid]) left = lmid + 1;//放弃lmid左边部分
            else right = rmid - 1; //放弃rmid右侧部分
        }
        return right;
    }
}
