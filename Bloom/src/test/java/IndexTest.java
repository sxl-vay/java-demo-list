public class IndexTest {
    public static void main(String[] args) {
        for (int i = 0; i < 2000000000; i++) {
            int index = getIndex(i, 25);
            int i1 = i / 32;
           if (!(i1 ==index)){
               System.out.println(Integer.toBinaryString(i));
               System.out.println(Integer.toBinaryString(i1));
               System.out.println(Integer.toBinaryString(index));
               return;
           }
        }

        }

    public static int getIndex(int i, int move) {
        /**
         * 假如i 是37859376
         * 二进制表示是
         * 0000 0010 0100 0001 1011 0000 0011 0000
         *在内存是256M的情况下，需要31根地址线
         * 也就是i的32个位中第一位是不需要的
         * 这就对应了p要清除的前置位数，
         * 又i的后五位对应的是MEMORY元素中的位置（2^5=32）
         * 所以数组的位置下标就是去掉前p位和后5位的结果。
         *也就是i中的中间save位信息
         * 在256M情况下i2固定不变，前p位和后五位为0，中间save位为1
         * 0111 1111 1111 1111 1111 1111 1110 0000
         *
         *
         */
        int b = i >>> 5 << 5;//清楚后置位的i
        //要清楚的前置位数
        int p = 32 - 5 - move;
        //要保留的位数
        int save = 32 - p - 5;
        int i1 = (-1) >>> 32 - move;
        //最终和清楚后置位的i进行与的数
        int i2 = i1 << 5;
        //索引下标
        int i3 = (i2 & b) >>> 5;
//        化简后得到：（编译期去除掉冗余括号后的最简结果）
        int index = (-1 >>> 32 - move << 5 & i >>> 5 << 5) >>> 5;
        int indexs = (-1 >>> 32 - move << 5 & i ) >>> 5;
        return i3;
    }

}
