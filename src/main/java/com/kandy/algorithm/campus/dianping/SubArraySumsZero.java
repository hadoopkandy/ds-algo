package com.kandy.algorithm.campus.dianping;
import java.util.HashMap;

/**
 * 找出数组中和为0的子数组
 *
 */
public class SubArraySumsZero {

	public static void main(String[] args) {
		subArraySumsZero();
	}
	/**
	 * 就是和累加的时候，比如累加到下表为0时候的和为1、累加到下表为4的元素和也为1，那么1-4之间的元素和肯定为0
	 */
	private static void subArraySumsZero() 
	{
	    int [] seed = new int[] {1,2,3,4,-9,6,7,-8,1,9};
	    int currSum = 0;

	    //key: is the currSum  value: the index
	    HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
	    
	    for(int i = 0 ; i < seed.length ; i ++)
	    {
	        currSum += seed[i];
	   
	        if(sumMap.get(currSum) != null)
	        {
	            System.out.println("subset : { " + (sumMap.get(currSum) + 1) + " - " + i + " }");
	            sumMap.put(currSum, i);
	        }
	        else
	        {
	            sumMap.put(currSum, i);
	        }
	    }
	}

}
