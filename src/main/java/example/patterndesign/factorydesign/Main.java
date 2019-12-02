package example.patterndesign.factorydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class Main {


    public static void main(String[] args) {

        PizzaStore nyPizzaStore = new NYPizzaStore();

        nyPizzaStore.orderPizza("cheese");

    }

}
