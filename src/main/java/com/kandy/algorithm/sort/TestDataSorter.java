package com.kandy.algorithm.sort;

import org.junit.Test;

/**
 * 排序测试类
 */
public class TestDataSorter {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
    DataSorter s = new DataSorter();

    @Test
    public void insertSort() {
        doTest(s.new InsertSort(), a);
    }

    @Test
    public void shellSort() {
        a = new int[]{1, 54, 6, 3, 78, 34, 12, 45, 56, 100};
        doTest(s.new ShellSort(), a);
    }

    @Test
    public void selectSort() {
        a = new int[]{1, 54, 6, 3, 78, 34, 12, 45};
        doTest(s.new SelectSort(), a);
    }

    @Test
    public void heapSort() {
        doTest(s.new HeapSort(), a);
    }

    @Test
    public void bubbleSort() {
        doTest(s.new BubbleSort(), a);
    }

    @Test
    public void quickSort() {
        a = new int[]{57, 68, 59, 52, 72, 28, 96, 33, 24, 19};
        doTest(s.new QuickSort(), a);
    }

    @Test
    public void mergeSort() {
        doTest(s.new MergeSort(), a);
    }

    @Test
    public void raidxSort() {
        doTest(s.new RadixSort(), a);
    }

    private void doTest(Sortable sorter, int a[]) {
        if (sorter instanceof AbstractSort) {
            AbstractSort sort = (AbstractSort) sorter;
            sort.printArray(a);
            return;
        }
        sorter.sort(a);
    }
}
