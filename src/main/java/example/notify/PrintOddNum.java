package example.notify;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class PrintOddNum {

    private volatile boolean isOdd = true;

    private int count = 0;

    private Object lock = new Object();

    public static void main(String[] args) {
        PrintOddNum printOddNum = new PrintOddNum();
        Thread thread = new Thread(printOddNum.new PrintOdd());
        thread.start();
        Thread thread1 = new Thread(printOddNum.new PrintEven());
        thread1.start();
    }

    class PrintOdd implements Runnable {
        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    if (isOdd) {
                        count ++;
                        System.out.println(Thread.currentThread().getName() + "奇数count : " + count);
                        isOdd = false;
                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    class PrintEven implements Runnable {
        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    if (!isOdd) {
                        count++;
                        isOdd = true;
                        System.out.println(Thread.currentThread().getName() + "偶数count : " + count);
                        try {
                            lock.notify();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
