package normaltest;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class NorTest {

    public static void main(String[] args) {

        SubA subA = new SubA("AAA");

        SubB subB = new SubB("BBB");

        subA.printName();

        subB.printName();

        subA.printName();

    }
}
