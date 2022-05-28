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

    private TrainStation trainStation = new TrainStation();

    public SellTickets getProxys() {
        SellTickets sellTickets = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK Proxy");
                        Object invoke = method.invoke(trainStation, args);
                        return  invoke;
                    }
                });
        return sellTickets;
    }

}
