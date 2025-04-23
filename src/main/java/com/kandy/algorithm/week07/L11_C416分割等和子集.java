package com.kandy.algorithm.week07;

/**
 * 分隔等和子集：从集合里选一个子集等于总和的一半
 * f[i][j]前i个数选出一些数，总和是j,是否可行
 * 物品：每个数  体积:nums[i]  价值：没有价值，变成可行性问题
 */
public class L11_C416分割等和子集 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        // Move to 1~n
        int[] num = new int[n + 1];
        for (int i = 1; i <= n; i++) num[i] = nums[i - 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) sum += num[i];
        if (sum % 2 == 1) return false; // 奇数不可能分两半，使得和相等
        sum /= 2;
        //f[i][j]前i个数选出一些数，总和是j,是否可行 true 或false
        //f[i][j] =f[i-1][j-nums[i]] || f[i-1][j]   选nums[i]:前i-1个数和达到j-nums[i]    不选nums[i]:前i-1个数总和达到j
        boolean[] f = new boolean[sum + 1]; //sum相当于背包问题里的m，m+1
        f[0] = true;
        for (int i = 1; i <= n; i++) //遍历物品
            for (int j = sum; j >= num[i]; j--) //倒序遍历体积
                f[j] = f[j] || f[j - num[i]];
        return f[sum];
    }

}
