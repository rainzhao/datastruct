package example.patterndesign.strategydesign.startegy3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
@example.patterndesign.strategydesign.startegy3.annotation.Strategy(name = "StrategyA")
public class ConcreteStrategyA implements Strategy {

    private static final Logger logger = LoggerFactory.getLogger(ConcreteStrategyA.class);

    @Override
    public void strategy(String input) {
        System.out.println(input);
        logger.debug("Strategy A for input {}: ", input);
    }

}
