package top.boking.proxy.jdk_proxy;

import top.boking.proxy.TrainStation;

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
    private TrainStation trainStation = new TrainStation();

    public   SellTickets getProxys() {
        SellTickets sellTickets = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public String invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object proxy1 = proxy;
                        int i = proxy1.hashCode();
                        System.out.println("i = " + i);

                        method.invoke(trainStation,args);
                        return "nihao";
                    }
                });
        return sellTickets;
    }

}
