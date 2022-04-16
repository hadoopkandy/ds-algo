package com.kandy.algorithm.campus.baidu;
import java.util.Scanner;
import java.util.Stack;
/**
 * 比大小
 * @author Kandy
 *
 */
public class SpecificSize {  


	public static void main(String[] args){	
		Scanner scanner=new Scanner(System.in);
		int n=scanner.nextInt();	
		int f[]=new int[13];
		int temp=0;
		String a[]=new String[n];  	  
		while(true){
			for(int i=0;i<n;i++){
				a[i]=scanner.next();
			}
			break;
		}
		f[1]=1;
		for(int i=2;i<=12;i++) {
			f[i]=f[i-1]*i; 
		}
		for(int j=0;j<n;j++){
			char[] arr=a[j].toCharArray();
			temp=0;
			for(int i=0;i<11;i++)
			{
				temp+=(fun(i,arr[i],arr)*f[11-i]);
			}
			System.out.println(f[12]-temp);
		}

	}

	public static int fun(int k,char ch,char[] arr)
	{
		int result=0;
		result='l'-ch;
		for(int i=0;i<k;i++)
			if(arr[i]>ch)
				result--;
		return result;
	}

}