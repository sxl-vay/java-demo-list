package top.boking.adapter.object_adapter;

/**
 * 计算机类
 * 此计算机类只提供了读取SD卡数据的接口，没有读取TF卡的接口所有要想读取TF卡的数据需要适配器类进行适配
 */
public class Computer {
    public String redaSD(SDCard sdCard){
        if (sdCard == null) {
            throw new NullPointerException("sd card is null");
        }
        return sdCard.redaSD();
    }
}
