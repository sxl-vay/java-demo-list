package top.boking.proxy.jdk_proxy;

import top.boking.proxy.SellTickets;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname ProxyFactory
 * @Description TODO
 * @Date 2022/1/16 13:26
 * @Created by shxl
 */
public class ProxyFactory {

    private TrainStation trainStation ;

    public void setTrainStation(TrainStation trainStation) {
        this.trainStation = trainStation;
    }

    public SellTickets getProxys() {
        SellTickets sellTickets = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(), (proxy, method, args) -> {
                    System.out.println("JDK Proxy");
                    String s1 = trainStation.toString();
                    System.out.println("s1 = " + s1);
                    Object invoke = method.invoke(trainStation, args);
                    return  invoke;
                });
        return sellTickets;
    }

}
