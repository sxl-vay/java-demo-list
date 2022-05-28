package top.boking.adapter.class_adapter;

public class Client {
    public static void main(String[] args) {
        //创建计算机对象
        Computer computer = new Computer();
        String s = computer.redaSD(new SDCardImpl());
        System.out.println("s = " + s);

        //使用computer类读取TF卡数据
        //使用适配器类
        String s1 = computer.redaSD(new SDAdapterTF());
        System.out.println("s1 = " + s1);
    }

}
