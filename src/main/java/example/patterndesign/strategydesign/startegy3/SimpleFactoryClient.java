package example.patterndesign.strategydesign.startegy3;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
public class SimpleFactoryClient {


    public static void main(String[] args) {

        SimpleFactoryContext context = new SimpleFactoryContext();
        context.getInstance("StrategyA").action("qqqqqq");
    }

}
