package top.boking.proxy.jdk_proxy;


import top.boking.proxy.SellTickets;

/**
 * @Classname Client
 * @Description TODO
 * @Date 2022/1/16 14:08
 * @Created by shxl
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTrainStation(new TrainStation());
        SellTickets proxys = proxyFactory.getProxys();
        String ni = proxys.sellTickets("hello");
        String s = proxys.toString();
        System.out.println("s = " + s);
        System.out.println("ni = " + ni);

    }

}
