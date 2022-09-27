package base.leetCode;

import java.util.Arrays;
import java.util.List;

/**
 * @author shxl
 * @data 2022/8/25 7:15
 **/
public class FindClosestElements {
    public static void main(String[] args) {
        int[] arr={2,4,6,9,11,99,101};
        int i = advanceInsertSortWithBinarySearch(arr, 7);
        System.out.println("i = " + i);
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = -1;
        int i1 = Arrays.binarySearch(arr, x);

        for (int i = 0; i < arr.length; i++) {

            if (x >= arr[i]) {
                index = i;
            }

        }

        return null;
    }

    public static int advanceInsertSortWithBinarySearch(int[] arr,int k,int fromIndex, int toIndex ) {
        int mid = (fromIndex+toIndex)>>1;

        if (false) {
            return mid;
        }

        if (arr[mid] > k) {
            advanceInsertSortWithBinarySearch(arr,k,fromIndex,mid-1);
        } else if (arr[mid] < k) {
            advanceInsertSortWithBinarySearch(arr,k,mid+1,toIndex);
        } else {

        }


    }
}
