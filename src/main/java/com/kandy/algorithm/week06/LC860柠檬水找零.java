package com.kandy.algorithm.week06;

import java.util.HashMap;

/**
 * 贪心-决策包容性
 */
public class LC860柠檬水找零 {
    public boolean lemonadeChange(int[] bills) {
        coins = new HashMap<>();
        for (int bill : bills) {
            coins.put(bill, coins.getOrDefault(bill, 0) + 1);
            if (!exchange(bill - 5)) return false;
        }
        return true;
    }

    boolean exchange(int amount) {
        for (int x : new int[]{20, 10, 5}) {
            while (amount >= x && coins.get(x) > 0) {
                amount -= x;
                coins.put(x, coins.get(x) - 1);
            }
        }
        return amount == 0;
    }

    HashMap<Integer, Integer> coins;
}
