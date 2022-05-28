package top.boking.factory.simplefactory;

import top.boking.factory.product.Product;
import top.boking.factory.product.ProductA;
import top.boking.factory.product.ProductB;

import java.math.BigDecimal;

public class SimpleFactory {
    public static Product createProduct(int x) {
        if (x == 1) {
            ProductB productB = new ProductB();
            productB.setName("产品B");
            productB.setPrice(new BigDecimal(100.10));
            return productB;
        } else if (x == 2) {
            ProductA productA = new ProductA();
            productA.setName("产品A");
            productA.setPrice(new BigDecimal(100));
            return productA;
        }
        return null;
    }

    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct(1);
        String pA = product.toString();
        System.out.println("s = " + pA);
        String pB = SimpleFactory.createProduct(2).toString();
        System.out.println("s1 = " + pB);

    }
}
