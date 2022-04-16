package com.kandy.algorithm.campus.jd;
import java.util.Scanner;
import java.util.Stack;

/*
 * 年终奖
200 120 400 150 180 300
150 250 360 120 200 130
350 300 250 100 500 260
100 150 260 320 100 150
500 130 260 100 200 170
160 100 250 200 600 200
 */
public class AnnualBonus {
	static Stack<Integer> result=new Stack<Integer>();
	static int maxN=0;
	public  int cal(Stack<Integer> r)
	{
		int num=0;
		for(int i=0;i<r.size();i++)
		{
			num+=r.get(i);
		}
		return num;
	}
	public  void search(int a[][],int x,int y)
	{
		if (x==5&&y==5)
		{
			int temp=cal(result)+a[5][5];
			if(temp>maxN)
				maxN=temp;
		}
		if (x<6&&y<6)
		{
			if(x+1<6)
			{
				result.push(a[x+1][y]);
				search(a,x+1,y);
				result.pop();
			}
			
			if(y+1<6)
			{
				result.push(a[x][y+1]);
				search(a,x,y+1);
				result.pop();
			}
		}
		else
		{
			return;
		}
	}

	public static void main(String args[])
    {
		AnnualBonus t=new AnnualBonus();
        Scanner cin = new Scanner(System.in);
        int[][] a=new int[6][6];
        while(true){
        	for(int i=0;i<6;i++){
        		for(int j=0;j<6;j++){
        			a[i][j]=cin.nextInt();
        		}
        	}       	
        	break;
        }  
        t.search(a,0,0);
        System.out.println(maxN);
        cin.close();
    }

}
