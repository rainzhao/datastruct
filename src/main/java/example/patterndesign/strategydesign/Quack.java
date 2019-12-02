package example.patterndesign.strategydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("quack ...");
    }
}
