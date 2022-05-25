package com.kandy.algorithm.week06;

import java.util.Arrays;

/**
 * 贪心-邻项交换
   贪心策略：actual - minimum 升序排序  actual越小，越往前排  minimum越大越往前排，以此顺序完成任务
   先做门槛高的反例：[5,8] [1,7]  门槛高的具有先做的趋势，实际耗费小的具有先做的趋势

   排序后顺序  1，7   5，8   实际消耗能力 max(7, 1 + 8) = 9
   排序后顺序  1，12   5，8   实际消耗能力 max(12,1 + 8) =12

   证明思路：
   1.假设一共有n个任务，令第i个任务为p，第i+1个任务为q，假设完成第i+2至n个任务需要的能量为s
   2.如果先完成p需要的能量为 Math.max( Math.max(s + actual[q], minimum[q]) + actual[p], minimum[p])
   3.如果先完成q需要的能量为 Math.max( Math.max(s + actual[p], minimum[p]) + actual[q], minimum[q])
   4.把两个式子展开得到     Math.max( Math.max(s + actual[q] + actual[p], minimum[q] + actual[p]),minimum[p])
               和        Math.max( Math.max(s + actual[p] + actual[q], minimum[p] + actual[q]), minimum[q])
   5.消去s + actual[q] + actual[p]
   6.若p比较优即先完成p，则Math.max(minimum[q]+ actual[p], minimum[p]) < Math.max(minimum[p]+actual[q], minimum[q])
   7.因为minimum[p]和minimum[q]都出现在相加的式子中，所以不会较大的项
   上式等价于 minimum[q] + actual[p] < minimum[p] + actual[q]
         actual[p] - minimum[p] < actual[q] - minimum[q]
   8.即把数组按照上述不等式排序，然后倒序考虑需要多少能量
 */
public class LC1665完成所有任务的最少初始能量 {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int ans = 0;
        for (int i = tasks.length - 1; i >= 0; i--) {
            //本身门槛比较高
            ans = Math.max(tasks[i][1], ans + tasks[i][0]);
        }
        return ans;
    }
}
