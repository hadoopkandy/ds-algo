package com.kandy.algorithm.week06;

/**
 * 贪心-决策范围扩展 决策包容性
 * 若a 能到 b1 b2 b3 ,而 b1 b2 b3 能到最远的位置分别是 c1 c2 c3,那应该从 a 跳到c1 c2 c3中最大的b
 */
class LC45跳跃游戏II {
    public int jump(int[] nums) {
        int now = 0;//起点
        int steps = 0;
        while (now < nums.length - 1) {
            int right = now + nums[now];
            // [now + 1, right] 可达范围
            if (right >= nums.length - 1) return steps + 1;//一跳就可以达到终点
            int maxPosition = right;
            int next = now;
            for (int i = now + 1; i <= right; i++) {
                if (i + nums[i] > maxPosition) {
                    maxPosition = i + nums[i];
                    next = i; //记录一下来源，下次从这里跳
                }
            }
            now = next; //now 下次去next这里
            steps++;
        }
        return steps;
    }
}
