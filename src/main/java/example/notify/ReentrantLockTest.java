package example.notify;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();

    public void execute() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " do something synchronize");
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " interrupted");
                Thread.currentThread().interrupt();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.execute();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.execute();
            }
        });
        thread1.start();
        thread2.start();
    }

}
