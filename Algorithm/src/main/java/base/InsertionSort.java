package base;

/**
 * @author shxl
 * @data 2022/5/28 1:01
 **/
public class InsertionSort {

    public static void insertion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int t = a[i];
            int index = 0;
            for (int j = i; j > 0; j--) {
                if (t < a[j - 1]) {
                    a[j] = a[j - 1];
                } else {
                    index = j;
                    break;
                }
            }
            a[index] = t;


        }
    }

}
