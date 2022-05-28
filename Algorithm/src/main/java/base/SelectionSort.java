package base;

/**
 * @author shxl
 * @data 2022/5/28 1:05
 **/
public class SelectionSort {

    /**
     * 选择排序
     * @param a 数组
     * @return
     */
    public static void  selectionSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] > a[minIndex]) {
                    minIndex = j;
                }
            }
            swap(a,minIndex,i);
        }

    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[j]^a[i];
        a[j] = a[j]^a[i];
        a[i] = a[j]^a[i];
    }
}
