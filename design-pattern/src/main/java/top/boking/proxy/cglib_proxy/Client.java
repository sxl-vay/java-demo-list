package top.boking.proxy.cglib_proxy;

import top.boking.proxy.cglib_proxy.TrainStation;

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        TrainStation proxy = proxyFactory.getProxy();
        String 上海 = proxy.sellTickets("上海");
        System.out.println("上海 = " + 上海);


    }
}
