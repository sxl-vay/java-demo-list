package top.boking.circularreference;

public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {14,65,7,4,687,43,6,2,498,74,8,96,4,68};
        quickSort(ints,0,ints.length-1);
        for (int anInt : ints) {
            System.out.println("anInt = " + anInt);

        }
    }
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
}