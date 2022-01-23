package top.boking.proxy;


/**
 * @Classname TrainStation
 * @Description TODO
 * @Date 2022/1/16 13:24
 * @Created by shxl
 */
public class TrainStation implements top.boking.proxy.jdk_proxy.SellTickets{
    public String sellTickets(String name){
        System.out.println(name+"!!");
        return "\t正在售卖去往\t"+name+"\t的火车票";
    }


}
