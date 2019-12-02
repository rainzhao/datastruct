package example.patterndesign.strategydesign.startegy3;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
@example.patterndesign.strategydesign.startegy3.annotation.Strategy(name = "StrategyB")
public class ConcreteStrategyB implements Strategy {

    private static final Logger logger = LoggerFactory.getLogger(ConcreteStrategyB.class);

    @Override
    public void strategy(String input) {
        logger.debug("StrategyB B for input: {}", input);
    }
}
