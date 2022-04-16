package com.kandy.algorithm.campus.bank;
public class Common { 

	public static void main(String[] args) { 

		Common cc = new Common(); 
		cc.commy(15, 19);
		System.out.println(cc.gcd(15, 19));
		cc.commb(15, 19); 

	}


   /**
    * 求a和b的最大公约数
    * @param a
    * @param b
    */
	public void commy( int a, int b) { 
		int i = 1; 
		int commyue = 0; 
		int  c = a; 
		if(c < b) 
			c = b; 
		while(i <= c) { 
			if(a % i == 0 && b % i == 0) 
				commyue = i; 
			    i++; 
		} 
	   System.out.println(commyue); 

	}

	/**
	 * 辗转相除法来计算两个非负数之间的最大公约数
	 * @param x
	 * @param y
     * @return
     */
	public long gcd(long x ,long y){
		if(y==0){
			return x;
		}
		else return gcd(y,x%y);
	}
   /**
    * 求a和b的最小公倍数
    * @param a
    * @param b
    */
	public void commb( int a, int b) { 
		int c = a; 
		int commbe = 0; 
		if(c < b) c = b; 
        while(c <= a * b) { 
			if(c % a == 0 && c % b == 0) { 
				commbe = c; 
				break; 
			} 
			c++; 
		} 
		System.out.println(commbe); 
	} 

} 
