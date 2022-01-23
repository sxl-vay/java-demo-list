package top.boking.utils;

import top.boking.bloom.MemorySize;

/**
 * 存储空间换算工具类
 */
public class MemoryUtils {

    /**
     * 输入地址位数，换算成可视化的存储空间大小返回
     *
     * @param adnum
     * @return
     */
    public static String getMemorySize(int adnum) {
        if(adnum<=3){
            return "to small";
        }
        String dw = "B";
        for (int j = 0; j < 5; j++) {
            if (j == 0) {
                dw = "B";
            }
            if (j == 1) {
                dw = "KB";
            }
            if (j == 2) {
                dw = "MB";
            }
            if (j == 3) {
                dw = "GB";
            }
            if (j == 4) {
                dw = "TB";
            }
            for (int i = 0; i < 10; i++) {
                if (adnum == (j * 10) + i + 3) {
                    int w = 1 << i;
                    return w + dw;
                }
            }
        }
        return "error";

    }

    /**
     * 已废弃
     *
     * @param ms
     * @return
     */
    @Deprecated
    public static int getNumberSize(MemorySize ms) {
        switch (ms) {
            case MB64:
                return 29;
            case MB128:
                return 30;

            case MB256:
                return 31;
            case MB512:
                return 32;
            case GB:
                return 33;
            case GB4:
                return 34;

            case GB8:
                return 35;
        }
        return -1;
    }

    /**
     * 输入存储的位数，输出可视化的存储空间大小返回
     * （位数只能是2指数）
     *
     * @param l
     * @return
     */
    public static String inBitOutSize(long l) {
        long copy = l;
        if (l == 0) {
            return "input is zero";
        }

        int size = 0, left = 0;
        while ((l & 1) == 0) {
            l >>>= 1;
            size++;
        }
        while ((copy & Long.MIN_VALUE) == 0) {
            copy <<= 1;
            left++;
        }
        if (left + size == 63)
            return getMemorySize(size);
        return "input formatter error";
    }

    /**
     * 判断输入值是否是2的指数
     * @param num
     * @return
     */
    private static boolean isFacByTwo(long num) {
        if (num == 0) {
            return false;
        }
        long copy = num;
        int left = 0, right = 0;
        while ((num & 1) == 0) {
            num >>>= 1;
            left++;
        }
        while ((copy & Long.MIN_VALUE) == 0) {
            copy <<= 1;
            right++;
        }

        return 63 == (right + left);
    }

    public static void main(String[] args) {

        String s = inBitOutSize(1L << 33);
        System.out.println("s = " + s);


    }

}
