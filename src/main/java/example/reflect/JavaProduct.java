package example.reflect;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class JavaProduct implements Product {

    public JavaProduct() {
    }

    @Override
    public void printProduct() {
        System.out.println("java product...");
    }
}
