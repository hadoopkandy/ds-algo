package com.kandy.algorithm.campus.meituan;
/**
 * 数组最大差值问题
 * @author Kandy
 *
 */
public class MaxDifference {
    public static int findMaxDifference(int[] arr, int len) {
    	 int res = 0;
    	    int diff = 0;
    	    for(int i = 1; i < len; i++) {
    	        diff = diff >= 0 ? diff + arr[i] - arr[i-1] : arr[i] - arr[i-1];
    	        if(diff > res) {
    	            res = diff;
    	        }
    	    }
    	    return res;
    }
    public static void main(String args[]){
    	int a[]={2,3,4,1,5,7,1,100,104};
    	System.out.println(findMaxDifference(a,a.length));
    }
}