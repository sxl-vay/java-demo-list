package top.boking.utils;

import top.boking.bloom.MemorySize;

public class MemoryForAddressNumber {
    public static String getMemorySize(int adnum) {
        /*if (adnum == 3){
            return "Bit";
        }
        if (adnum == 13){
            return "KB";
        }
        if (adnum == 23){
            return "MB";
        }*/
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

    public static String inBitOutSize(long l) {
        if (l == 0){
            return "input is zero";
        }
        int size = 0;
        while ((l & 1) == 0) {

            l >>>= 1;
            size++;
        }
        return getMemorySize(size);
    }


    public static void main(String[] args) {
        for (int i = 1; i < 60; i++) {
            System.out.println("i = " + i);
            String memorySize = getMemorySize(i);
            System.out.println("memorySize = " + memorySize);

        }
        int numberSize = getNumberSize(MemorySize.MB256);
        System.out.println("numberSize = " + numberSize);
        System.out.println("-------------------------");
        long i = (long) (8 * 1024) * 1024 * 1024*1024;
        System.out.println("i = " + i);
        String s = inBitOutSize(i );
        System.out.println("s = " + s);
    }

}
