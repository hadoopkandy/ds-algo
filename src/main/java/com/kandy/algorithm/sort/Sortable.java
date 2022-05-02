package com.kandy.algorithm.sort;

/**
 * Java常用排序算法/程序员必须掌握的8大排序算法
 * http://blog.csdn.net/qy1387/article/details/7752973
 * 1）插入排序（直接插入排序、希尔排序）
 * 2）选择排序（直接选择排序、堆排序）
 * 3）交换排序（冒泡排序、快速排序）
 * 4）归并排序
 * 5）分配排序（基数排序）
 * 所需辅助空间最多：归并排序
 * 所需辅助空间最少：堆排序
 * 平均速度最快：快速排序
 * 不稳定：简单选择排序、快速排序，希尔排序，堆排序。
 */
public interface Sortable {
    public abstract void sort(int a[]);
}
