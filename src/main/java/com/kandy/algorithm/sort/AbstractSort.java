package com.kandy.algorithm.sort;

import java.util.Arrays;


public abstract class AbstractSort implements Sortable {

    public void printArray(int a[]) {
        System.out.println(Arrays.toString(a));
    }

    /**
     * 三段式的数据交换
     *
     * @param data
     * @param i
     * @param j
     */
    protected void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
