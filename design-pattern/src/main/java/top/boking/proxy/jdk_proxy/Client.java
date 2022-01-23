package top.boking.proxy.jdk_proxy;


/**
 * @Classname Client
 * @Description TODO
 * @Date 2022/1/16 14:08
 * @Created by shxl
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        SellTickets proxys = proxyFactory.getProxys();
        String ni = proxys.sellTickets("ni");
        System.out.println("ni = " + ni);

    }

}
