package example.patterndesign.singletondesign.notsafeuse;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class SafeGate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "NoWhere";

    /**
     * 防止赋值操作有多个线程并发执行，导致值交错
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.name = name;
        this.address = address;
        this.counter++;
        check();
    }

    @Override
    public synchronized String toString() {
        return "No." + counter + ": " + name + ", " + address;
    }

    private void check() {
        // 因为pass方法加了同步，所以check方法不必加上同步
        if(name.charAt(0) != address.charAt(0)) {
            System.out.println("****** BROKEN ******" + toString());
        }
    }
}
