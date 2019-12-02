package example.patterndesign.strategydesign.startegy3;

import example.patterndesign.strategydesign.startegy3.annotation.Strategy;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoyu
 * @date 2019-08-15
 */
public class SimpleFactoryContext {

    private static final Logger logger = LoggerFactory.getLogger(SimpleFactoryContext.class);

    private static Map<String, Class> allStrategies;

    static {

        Reflections reflections = new Reflections("example.patterndesign.strategydesign.startegy3");
        Set<Class<?>> annotatedClasses =
                reflections.getTypesAnnotatedWith(Strategy.class);

        allStrategies = new ConcurrentHashMap<String, Class>(16);

        for (Class<?> classObject : annotatedClasses) {
            Strategy strategy = classObject.getAnnotation(Strategy.class);
            allStrategies.put(strategy.name(), classObject);
        }
        allStrategies = Collections.unmodifiableMap(allStrategies);
    }

    private example.patterndesign.strategydesign.startegy3.Strategy strategy;

    public SimpleFactoryContext getInstance(String name) {
        try {
            Class orDefault = allStrategies.getOrDefault(name, null);
            if (orDefault != null) {
                strategy = (example.patterndesign.strategydesign.startegy3.Strategy) orDefault.newInstance();
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return this;
    }

    public void action(String name) {
        strategy.strategy(name);
    }


}
