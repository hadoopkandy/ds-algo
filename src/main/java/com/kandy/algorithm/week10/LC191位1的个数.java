package com.kandy.algorithm.week10;

/*
位运算符
& 按位与
| 按位或
~ 按位反
^ 按位异或  相同得0，不同得1 （不进位加法）
<< 左移  高位舍弃，低位补0
>> 右移  低位舍弃，高位补0 （逻辑右移）  低位舍弃，高位补符号位 （算术右移，正数补0，负数补1）

指定位置的位运算：
第n位：  （x>>n）& 1 思路：把第0~n-1位右移掉，把第n位变成个位，再&1
第n位置1：x|(1<<n) 思路：1向左移动，移到跟这位对齐，然后做或运算
第n位置0：x&(~(1<<n)) 思路：1向左移动，移到跟这位对齐,然后取反，然后做与运算
第n位取反：x^(1<<n) 思路：1向左移动，移到跟这位对齐，然后做异或运算

把x最右边的n位清零：x & (~0<<n)  思路：非1的左移n位（右边n位是0），然后做&操作  11110000
把x最高位至第n位（含）清零: x & ((1<<n)-1)  思路： 00001111


位运算实战要点：
%2  ->  & 1
x/2  ->  x>>1
lowerbit 得到最低位的1  x & -x 或 x & (-x + 1)
         清零最低位的1  x  = x &(x-1)
 */
public class LC191位1的个数 {
    //方法1：右移1位 c++可以跑过，java跑不过
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n > 0) {
            if ((n & 1) == 1) cnt++;
            n = n >> 1;
        }
        return cnt;
    }

    //方法2：得到第i位  c++可以跑过，java可以跑过
    public int hammingWeight2(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++)
            //第i位置
            if (((n >> i) & 1) == 1) count++;
        return count;
    }

    //方法3:lowbit c++可以跑过，java跑不过
    public int hammingWeight3(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt++;
            n = n & (n - 1);
        }
        return cnt;
    }
}
