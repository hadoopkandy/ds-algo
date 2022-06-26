package com.kandy.algorithm.week10;

public class 树状数组模板_LC307区域和检索 {
    class NumArray {

        public NumArray(int[] nums) {
            n = nums.length;
            a = new int[n + 1]; // 1~n
            c = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = nums[i - 1]; // 下标变为从1开始
                add(i, a[i]);
            }
        }

        public void update(int index, int val) {
            index++; // 下标从1开始
            int delta = val - a[index];
            add(index, delta);
            a[index] = val;
        }

        public int sumRange(int left, int right) {
            left++;
            right++; // 下标从1开始
            return query(right) - query(left - 1);
        }

        int query(int x) {
            int ans = 0;
            for (; x > 0; x -= lowbit(x)) ans += c[x];
            return ans;
        }

        void add(int x, int delta) {
            for (; x <= n; x += lowbit(x)) c[x] += delta;
        }

        int lowbit(int x) {
            return x & (-x);
        }

        int n;
        int[] a;
        int[] c;
    }
}
