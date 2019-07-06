package example.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaoyu
 * @date 2019-03-09
 */
public class WaitNotify {
    static boolean flag = true;

    static Object lock = new Object();


    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "waitThread");
        waitThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "falg is true  "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                        System.out.println("hahahahha");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "falg is false..  "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock notify  "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock aging...  "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                /*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }

        }
    }
}
