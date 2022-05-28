package top.boking.circularreference;

import java.util.Arrays;

/**
 * @author shxl
 * @data 2022/5/26 20:51
 **/
public class Sort2 {
    public static void main(String[] args) {


        int[] a = {9,8,7,6,5,4,3,2,1};
        merge(a,5,6,6);
        mergeSort(a,0,a.length+1);
        String s = Arrays.toString(a);
        System.out.println("s = " + s);

    }

    public static void mergeSort(int[] a,int left,int right) {
        int length = right - left;
        if ( length== 0) {
            return ;
        }

        int middle = left+(length>>1);
        mergeSort(a,left,middle);
        mergeSort(a,middle+1,right);

        merge(a,left,middle+1,right);
        System.out.println(Arrays.toString(a));
    }

    private static void merge(int[] a,int leftPot,int rightPot,int edge) {
        int middle = rightPot;
        
        int tem[] = new int[edge-leftPot+1];
        
        int i = leftPot;
        int j = middle;
        int k = 0;
        while (i < middle && j <edge) {
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
        while (j<edge) tem[k++] = a[j++];

        int i1 = edge - leftPot+1;
        for (int i2 = 0; i2 < i1; i2++) {
            a[leftPot+i2] = tem[i2];
        }

    }


    public static void insertion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int t = a[i];
            int index = 0;
            for (int j = i; j > 0; j--) {
                if (t > a[j - 1]) {
                    a[j] = a[j - 1];
                } else {
                   index = j;
                    break;
                }
                System.out.println("f");
            }
            a[index] = t;


        }
    }
    /**
     * 冒泡
     * 进行了优化，加入了有序标志位，如果一个完成的内存循环，一次swap都没有执行，说明这个数据已经有序，直接结束外层循环即可。
     * @param a 数组
     */
    public static void bubble(int[] a) {
        System.out.println("lengTh:"+a.length);
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
            System.out.println("sxl:"+i);
        }
    }

    /**
     * 快速排序
     * @param a 数组
     * @param low 低位指针
     * @param height 高位指针
     */
    public static void quickSort(int[] a, int low, int height) {
        int left = low;
        int right = height;
        int base = a[low];

        //递归的跳出判断
        if (low >= height) {
            return;
        }

        //当高位指针和低位指针没有相等时继续遍历数组
        while (low < height) {
            //从base的对立面开始寻找比base小的数据
            while (a[height] >= base && low < height) {
                height--;
            }

            //将高位的小数据放到低位（这里的low索引下的数据已经被base记录不会丢失）
            a[low] = a[height];

            //从低索引开始寻找比base大的树
            while (a[low] <= base && low<height) {
                low++;
            }
            //将低位的大数据放到高位（height索引下的数据已经记录到了低索引处不会丢失）
            a[height] = a[low];
        }
        //while循环结束找到了low和height相等的地方，base归位
        a[low] = base;

        //根据low的位置将数组分割层为两个部分再次进行遍历操作。这里需要注意的是，使用low进行分割的时候left和right都不可以写死为0和length-1这样很有可能进入死循环
        quickSort(a,left,low-1);
        quickSort(a,low+1,right);

    }


    /**
     * 选择排序
     * @param a 数组
     * @return
     */
    public static   int[] selectionSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] > a[minIndex]) {
                    minIndex = j;
                }
            }
            swap(a,minIndex,i);
        }

        return a;
    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[j]^a[i];
        a[j] = a[j]^a[i];
        a[i] = a[j]^a[i];
    }
}
