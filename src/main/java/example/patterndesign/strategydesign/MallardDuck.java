package example.patterndesign.strategydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithThings();
    }

    @Override
    public void display() {
        System.out.println(" I'm real Mallard Duck");
    }
}
