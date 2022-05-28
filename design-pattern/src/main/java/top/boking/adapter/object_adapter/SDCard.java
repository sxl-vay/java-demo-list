package top.boking.adapter.object_adapter;

/**
 * 目标接口
 */
public interface SDCard {
    //从sd卡读取数据
    String redaSD();
    //写入数据到sd卡
    void writeSD(String msg);

}
