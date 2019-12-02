package example.notify;

/**
 * @author zhaoyu
 * @date 2019/11/26
 * @description: TODO
 */
public class PrintOddMyTest {

    private volatile boolean isOdd = true;

    private int count = 0;

    private Object lock = new Object();

    public static void main(String[] args) {
        PrintOddMyTest printOddMyTest = new PrintOddMyTest();
        printOddMyTest.process();
    }

    public void process() {
        PrintOdd printOdd = new PrintOdd();
        PrintEven printEven = new PrintEven();

        printEven.start();
        printOdd.start();

    }

    class PrintOdd extends Thread {
        @Override
        public void run() {
            while (count < 100) {

                synchronized (lock) {
                    if (isOdd) {
                        count ++;
                        System.out.println(Thread.currentThread().getName() + "_" + count );
                        isOdd = false;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    class PrintEven extends Thread {
        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    if (!isOdd) {
                        count ++;
                        System.out.println(Thread.currentThread().getName() + "_" + count );
                        isOdd = true;
                        try {
                            lock.notify();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
