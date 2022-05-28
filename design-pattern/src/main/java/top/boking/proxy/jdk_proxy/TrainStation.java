package top.boking.proxy.jdk_proxy;


import top.boking.proxy.SellTickets;

/**
 * @Classname TrainStation
 * @Description TODO
 * @Date 2022/1/16 13:24
 * @Created by shxl
 */
public class TrainStation implements SellTickets {
    public String sellTickets(String name){
        System.out.println(name+"!!");
        return "\t正在售卖去往\t"+name+"\t的火车票";
    }


}
