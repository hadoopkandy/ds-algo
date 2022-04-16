package com.kandy.algorithm.sort;

/**
 * 基于快速排序的TOPK算法 
 * 类似于快速排序，首先选择一个划分元，如果这个划分元的序号index刚好等于k，那么这个划分元以及左边的数，刚好组成了top-k small data;
 * 如果index>k, 那top-k small data在index的左边，那么就继续递归从index-1和数中选取top-k.如果index < k,
 * 那么说明还要从index的右边，选取top-(k-index) small data.
 * http://blog.csdn.net/fanzitao/article/details/7617223
 *
 */
public class TopK_Quick {

	public static int Partition(int a[],int low,int high)
	{
		a[0]=a[low];
		int pivokey = a[low];
		while(low<high)
		{
			while(low<high && a[high]>=pivokey) --high;
			a[low] = a[high];
			while(low<high && a[low]<=pivokey) ++low;
			a[high]= a[low];
		}
		a[low]=a[0];
		return low;
	}
	
	public static void display(int a[],int k)
	{
		for(int i=1;i<=k;i++)
		{
			System.out.print(a[i]+" ");
		}
	}
	public static int selectK(int a[],int start,int end,int k)
	{
		int index = 0;
		if(start<end)
		{
			index = Partition(a,start,end);
			System.out.println("index:"+index);
			if(index == k)//正好找到第k大的数
			{
				index = k;
			}else if(index < k)//还要从index的右边找k-index个数
			{
				index = selectK(a,index+1,end,k-index);
			}else if(index > k)//k个数都在Index的左边
			{
				index = selectK(a,start,index-1,k);
			}
		}
		return index;

	}
	public static void main(String args[])
	{
		int k=3;
		int a[]={49,38,29,65,97,76,13,27,49,22,19};
		if(k>0&&k<=a.length-1)
		{
			selectK(a,1,a.length-1,k);
			display(a,k);
		}else{
			System.out.println("Are You Kidding Me?");
		}
		
	}
}
