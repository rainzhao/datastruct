package normaltest;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public abstract class Parent {

    private String keyName;

    public Parent(String keyName) {
        this.keyName = keyName;
    }


    public void printName() {

        System.out.println(this.keyName);
    }
}
