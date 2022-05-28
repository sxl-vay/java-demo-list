package top.boking.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.boking.proxy.cglib_proxy.TrainStation;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    private TrainStation trainStation = new TrainStation();
    public TrainStation getProxy(){
        //创建Enhancer对象，类似于JDK代理中的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置父类的字节码对象
        enhancer.setSuperclass(TrainStation.class);
        //设置回调函数
        enhancer.setCallback(this);

        //创建代理对象
        TrainStation trainStation =(TrainStation) enhancer.create();


        return trainStation;
    }

    @Override
    public String intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib代理");
        String invoke =(String) method.invoke(trainStation, objects);


        return invoke+":代理";
    }
}
