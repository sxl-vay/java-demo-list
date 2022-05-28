package top.boking.factory.factoryMethod;

import top.boking.factory.product.Product;
import top.boking.factory.product.ProductB;

import java.math.BigDecimal;

public class BFactory implements Factory {

    @Override
    public Product createProduct() {
        ProductB productB = new ProductB();
        productB.setName("factoryB");
        productB.setPrice(new BigDecimal(99.90));
        return productB;
    }
}
