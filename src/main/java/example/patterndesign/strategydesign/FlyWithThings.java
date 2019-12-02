package example.patterndesign.strategydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class FlyWithThings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("fly with things ...");
    }
}
