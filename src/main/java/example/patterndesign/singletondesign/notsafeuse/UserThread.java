package example.patterndesign.singletondesign.notsafeuse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoyu
 * @date 2019-08-22
 */
public class UserThread extends Thread {

    private final SafeGate gate;

    private final String myname;

    private final String myaddress;

    public UserThread(SafeGate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    @Override
    public void run() {
        System.out.println(myname + " BEGIN");
        while (true) {
            gate.pass(myname, myaddress);
        }
    }

    public static void main(String[] args) {

        // 非线程安全
        /*NotSafeGate gate = new NotSafeGate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();*/
        // 线程安全
        SafeGate gate = new SafeGate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();
    }
}
