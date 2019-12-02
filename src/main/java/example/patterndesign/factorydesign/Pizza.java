package example.patterndesign.factorydesign;

import java.util.ArrayList;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();


    public void prepare() {

        System.out.println("Preparing " + name);
        System.out.println("Tossing dough");


    }

    public void bake() {
    }

    public void cut() {
    }

    public void box() {
    }
}
