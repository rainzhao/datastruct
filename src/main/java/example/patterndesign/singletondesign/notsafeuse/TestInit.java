package example.patterndesign.singletondesign.notsafeuse;

import example.pattern.abstractfactory.Test;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class TestInit {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final String test = "1234";

    public static TestInit getInstance() {
        return ThreadPoolManagerHolder.instance;
    }

    private static class ThreadPoolManagerHolder {
        public static TestInit instance = new TestInit();
    }

    public static void main(String[] args) {
        TestInit instance = TestInit.getInstance();

        TestInit instance1 = TestInit.getInstance();

        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }


}
