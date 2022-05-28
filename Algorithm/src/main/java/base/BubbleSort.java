package base;

/**
 * @author shxl
 * @data 2022/5/28 1:04
 **/
public class BubbleSort {
    /**
     * 冒泡
     * 进行了优化，加入了有序标志位，如果一个完成的内存循环，一次swap都没有执行，说明这个数据已经有序，直接结束外层循环即可。
     * @param a 数组
     */
    public static void bubble(int[] a) {
        int f = 1;
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length - i-1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a,j,j+1);
                    f=0;
                }
            }
            if (f == 1) {
                break;
            }
            f = 1;
        }
    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[j]^a[i];
        a[j] = a[j]^a[i];
        a[i] = a[j]^a[i];
    }
}
