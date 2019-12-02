package example.patterndesign.factorydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza =  new NYStyleCheesePizza();
        }

        return pizza;
    }
}
