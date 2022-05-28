package base;

/**
 * @author shxl
 * @data 2022/5/28 1:02
 **/
public class MergeSort {

    public static void mergeSort(int[] a) {
        mergeSortinner(a,0,a.length-1);
    }


    private static void mergeSortinner(int[] a, int left, int right) {
        int length = right - left;
        if ( length== 0) {
            return ;
        }

        int middle = left+(length>>1);
        mergeSortinner(a,left,middle);
        mergeSortinner(a,middle+1,right);

        merge(a,left,middle+1,right);
    }

    private static void merge(int[] a,int leftPot,int rightPot,int edge) {
        int middle = rightPot;

        int tem[] = new int[edge-leftPot+1];

        int i = leftPot;
        int j = middle;
        int k = 0;
        while (i < middle && j <=edge) {
            if (a[i] < a[j]) {
                tem[k] = a[i];
                k++;
                i++;
            } else {
                tem[k] = a[j];
                k++;
                j++;
            }
        }
        while (i<middle) tem[k++] = a[i++];
        while (j<=edge) tem[k++] = a[j++];

        int i1 = edge - leftPot+1;
        for (int i2 = 0; i2 < i1; i2++) {
            a[leftPot+i2] = tem[i2];
        }

    }

}
