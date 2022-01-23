package top.boking.bloom;

/**
 * @Classname Bloom
 * @Description Bloom过滤器的顶层接口
 * @Date 2022/1/15 16:26
 * @Created by shxl
 */
public interface Bloom {

    /**
     * 没有同步的普通插入方法
     * @param key
     * @return
     */
    boolean insertKey(String key) ;
    /**
     * 安全控制的插入方法
     * @param
     * @return
     */
    boolean insertKeySecurity(String key);
    /**
     * 快速插入方法（没有安全控制，最为快速的插入方法）
     * @param key
     * @return
     */
    void insertFast(String key);
    /**
     * 检查当前可以是否在bloom中
     * @param key
     * @return
     */
    boolean checkKey(String key);

    /**
     * 获取当前bloom的样本容量
     * @return
     */
    long getNowDataNumber();

    /**
     * 获取当前的失误率
     * @return
     */
    double getNowMissProbability();
}