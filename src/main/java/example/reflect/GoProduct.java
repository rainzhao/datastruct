package example.reflect;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class GoProduct implements Product {

    public GoProduct() {
    }

    @Override
    public void printProduct() {
        System.out.println("go product...");
    }
}
