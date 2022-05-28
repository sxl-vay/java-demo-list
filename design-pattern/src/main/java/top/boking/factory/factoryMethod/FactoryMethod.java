package top.boking.factory.factoryMethod;

import top.boking.factory.product.Product;

public class FactoryMethod {
    public static void main(String[] args) {
        Factory factoryA = new AFactory();
        Factory factoryB = new AFactory();
        Product productA = factoryA.createProduct();
        Product productB = factoryB.createProduct();

        System.out.println("productB = " + productB);
        System.out.println("productA = " + productA);

    }
}
