package com.kandy.algorithm.week06;

/**
 * 贪心-决策范围扩展
 */
public class LC45跳跃游戏II {
    public int jump(int[] nums) {
        int now = 0;//起点
        int ans = 0;
        while (now < nums.length - 1) {
            int right = now + nums[now];
            // [now + 1, right] 可达范围
            if (right >= nums.length - 1) return ans + 1;//再跳一步就可以达到终点
            int nextRight = right;
            int next = now;
            for (int i = now + 1; i <= right; i++) {
                if (i + nums[i] > nextRight) {
                    nextRight = i + nums[i];
                    next = i; //记录一下来源，下次从这里跳
                }
            }
            now = next;
            ans++;
        }
        return ans;
    }
}
