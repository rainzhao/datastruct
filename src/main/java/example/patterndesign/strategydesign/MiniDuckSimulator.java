package example.patterndesign.strategydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();

    }
}
