package base.util;

import base.*;

import java.util.Arrays;

/**
 * @author shxl
 * @data 2022/5/26 20:51
 **/
public class Sort {
    public static void main(String[] args) {

        CheckMethod.check(Sort.class,"quickSort");

    }

    /**
     * 对外提供的归并排序接口
     *
     * @param a
     */

    public static void mergeSort(int[] a) {
        MergeSort.mergeSort(a);
    }


    public static void insertion(int[] a) {
        InsertionSort.insertion(a);
    }

    /**
     * 冒泡
     * 进行了优化，加入了有序标志位，如果一个完成的内存循环，一次swap都没有执行，说明这个数据已经有序，直接结束外层循环即可。
     *
     * @param a 数组
     */
    public static void bubble(int[] a) {
        BubbleSort.bubble(a);
    }

    /**
     * 快速排序
     *
     * @param a 数组
     */
    public static void quickSort(int[] a) {
        QuickSort.quickSort(a);

    }


    /**
     * 选择排序
     *
     * @param a 数组
     * @return
     */
    public static void selectionSort(int[] a) {
        SelectionSort.selectionSort(a);
    }

    public static void print(int a[]) {
        System.out.println(Arrays.toString(a));
    }
}
