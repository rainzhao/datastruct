package example.patterndesign.strategydesign.startegy3;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
public class SimpleContext {

    private Strategy strategy;

    public SimpleContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void action(String input) {
        strategy.strategy(input);
    }

}
