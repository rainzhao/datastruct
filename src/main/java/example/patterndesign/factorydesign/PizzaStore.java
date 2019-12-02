package example.patterndesign.factorydesign;

/**
 * @author zhaoyu
 * @date 2019-08-14
 *
 *
 * 工厂模式
 * 工厂方法用来处理对象的创建，并将这样的行为封装在子类中。这样，客户程序中关于超类的代码和子类对象创建代码解耦了
 *
 */
public abstract class PizzaStore {


    public Pizza getPizza(String type) {
        return createPizza(type);
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = getPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);

}
