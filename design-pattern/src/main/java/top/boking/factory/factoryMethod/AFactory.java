package top.boking.factory.factoryMethod;

import top.boking.factory.product.Product;
import top.boking.factory.product.ProductA;

import java.math.BigDecimal;

public class AFactory implements Factory {
    @Override
    public Product createProduct() {
        ProductA productA = new ProductA();
        productA.setName("factoryA");
        productA.setPrice(new BigDecimal(89.90));
        return productA;
    }
}
