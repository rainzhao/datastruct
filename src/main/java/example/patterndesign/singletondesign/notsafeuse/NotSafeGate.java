package example.patterndesign.singletondesign.notsafeuse;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class NotSafeGate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "NoWhere";

    public void pass(String name, String address) {
        this.name = name;
        this.address = address;
        this.counter++;
        check();
    }

    @Override
    public String toString() {
        return "No." + counter + ": " + name + ", " + address;
    }

    private void check() {
        if(name.charAt(0) != address.charAt(0)) {
            System.out.println("****** BROKEN ******" + toString());
        }
    }
}
