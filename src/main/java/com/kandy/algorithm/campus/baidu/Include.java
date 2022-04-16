package com.kandy.algorithm.campus.baidu;

import java.util.Scanner;
/**
   Problem Description:
         判断字符串b的所有字符是否都在字符串a中出现过，a、b都是可能包含汉字的字符串。b中重复出现的汉字，那么a中也要至少重复相同的次数。汉字使用gbk编码（简单的说，用两个字节表示一个汉字，高字节最高位为1的代表汉字，低字节最高位可以不为1）。
   int is_include(char *a, char *b); 
         返回0表示没有都出现过，返回1表示都出现过。 
         请设计一个算法。
	输入
	从标准输入中读取输入内容，文件中的内容如下
	字符串a\n字符串b​
	输出
	标准输出中输出0或者1
	
样例输入
aaaabbbcccdddss
abc
样例输出
1​
 */
public class Include {	 
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String s1=scanner.nextLine();
		String s2=scanner.nextLine();
		System.out.println(include(s1,s2));
	}	
	public static int include(String s1,String s2){
		int count=0;
		for(int i=0;i<s2.length();i++){
			for(int j=0;j<s1.length();j++){
				if(s2.charAt(i)==s1.charAt(j)){
					s1.replace(s1.charAt(j), ' ');
					count++;
					break;
				}
			}
		}
		if(count==s2.length()){
			return 1;
		}else{
			return 0;
		}	
	}	
}