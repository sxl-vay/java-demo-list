package top.boking.factory.abstractfactory;

import top.boking.factory.abstractfactory.factory.DellFactory;
import top.boking.factory.abstractfactory.factory.HUAWEIFactory;
import top.boking.factory.abstractfactory.factory.IFactory;
import top.boking.factory.abstractfactory.factory.LenovoFactory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        IFactory huawei = new HUAWEIFactory();
        System.out.println("huawei.createBuletooth() = " + huawei.createBuletooth());
        System.out.println("huawei.createComputer() = " + huawei.createComputer());
        System.out.println("huawei.createKeyboard() = " + huawei.createKeyboard());

        IFactory dell = new DellFactory();
        System.out.println("dell.createBuletooth() = " +dell.createBuletooth());
        System.out.println("dell.createComputer() = " + dell.createComputer());
        System.out.println("dell.createKeyboard() = " + dell.createKeyboard());
        IFactory lenovo = new LenovoFactory();
        System.out.println("lenovo.createBuletooth() = " +lenovo.createBuletooth());
        System.out.println("lenovo.createComputer() = " + lenovo.createComputer());
        System.out.println("lenovo.createKeyboard() = " + lenovo.createKeyboard());


    }
}
