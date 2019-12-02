package example.patterndesign.simplefactorydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class SimplePizzaFactory {


    public Pizza createPizza(String type) {

        Pizza pizza = null;

        if(type.equals("cheese")) {
            pizza = new CheesePizza();
        }
        // else if()...

        return pizza;

    }

}
