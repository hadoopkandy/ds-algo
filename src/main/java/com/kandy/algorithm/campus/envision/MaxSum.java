package com.kandy.algorithm.campus.envision;
/**
 * Maximum Continuous Subsequence Sum
 * 输入一个整数序列,求出其中连续子序列求和的最大值。
 * @author Kandy
 *
 */
public class MaxSum {

	public static void main(String[] args) {
	    int a[] = {-4 , 3 ,56 , -15 , 34 , 0 , -14 , 4};
		//int a[] = {-4 ,-5,-6};
		//int a[] = {1,-1,3,50,-30,100,4};
		//System.out.println(maxSubSumCubic(a));
		//System.out.println(maxSubSumQuadratic(a));
		System.out.println(maxSubSumLinear(a));
	}
	public static int maxSubSumCubic(int[] array) {
		int maxSum = 0;  //最大子序列求和

		//start表示要求和的子序列的开始索引，end表示结束索引
		for(int start = 0; start < array.length; start++) {

			for(int end = start; end < array.length; end++) {

				int thisSum = 0;   //当前子序列求和

				//求出array[start]~array[end]子序列的和
				for(int index = start; index <= end; index++) {
					thisSum += array[index];
				}

				//判断是否大于之前得到的最大子序列求和
				if(thisSum > maxSum) {

					maxSum = thisSum;

				}

			}

		}
		return maxSum;

	}
	public static int maxSubSumQuadratic(int[] array) {

		int maxSum = 0;  //最大子序列求和

		//start表示要求和的子序列的开始索引，end表示结束索引

		for(int start = 0; start < array.length; start++) {

			int thisSum = 0;   //当前子序列求和

			for(int end = start; end < array.length; end++) {                            

				//已求得的array[start]~array[end-1]子序列的和加上array[end]

				//得到array[start]~array[end]子序列的和

				thisSum += array[end];                         

				//判断是否大于之前得到的最大子序列求和

				if(thisSum > maxSum) {

					maxSum = thisSum;

				}

			}

		}

		return maxSum;

	}
	public static int maxSubSumLinear(int[] array) {

		int maxSum = 0, thisSum = 0;

		for(int j = 0; j < array.length; j++) {

			thisSum += array[j];

			if (thisSum < 0) {
				thisSum = 0;
			}

			else if(thisSum > maxSum) {
				maxSum = thisSum;
			}

		}
		return maxSum;
	}


}
