package example.reflect;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class ProductFactory {
    public Product getProduct(Object product) throws Exception {
        Class<Product> productClass = (Class<Product>) product;
        Product product1 = productClass.newInstance();
        product1.printProduct();
        return product1;
    }
}
