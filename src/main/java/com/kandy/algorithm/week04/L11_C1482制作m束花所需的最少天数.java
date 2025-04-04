package com.kandy.algorithm.week04;

/**
 * 后继型
 */
public class L11_C1482制作m束花所需的最少天数 {
    public int minDays(int[] bloomDay, int m, int k) {
        int latestBloom = 0;
        for (int bloom : bloomDay) {
            latestBloom = Math.max(latestBloom, bloom);
        }
        int left = 0; //下界 0
        int right = latestBloom + 1; //上界最大值+1  表示无解
        while (left < right) {
            int mid = (left + right) / 2;
            if (isValidOnDay(bloomDay, m, k, mid)) right = mid;
            else left = mid + 1;
        }
        if (right == latestBloom + 1) right = -1; // 无解
        return right;
    }

    // 判定：第T天的开花情况，能否制作m束花，每束花是连续的k朵
    private boolean isValidOnDay(int[] bloomDay, int m, int k, int day) {
        int consecutive = 0;  // 已经连续开了几朵
        int bouquet = 0;  // 花束数量
        // [x, x, x, x, _, x, x]
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) { // 已经开了
                consecutive++;
                //满足连续的k朵，计入答案bouquet
                if (consecutive == k) {
                    bouquet++;
                    consecutive = 0; //记得将连续数量清0
                }
            } else {
                consecutive = 0;
            }
        }
        //做的花的数量是否够m束
        return bouquet >= m;
    }
}
// 花越开越多，T天如果可行（能制作），T+1,T+2,...天都可行（能制作）- 单调分段
// 判定：mid天的开花情况，能否制作m束花，每束花是连续的k朵
