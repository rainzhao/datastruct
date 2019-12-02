package example.notify;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019/11/26
 * @description: TODO
 */
public class SaleTicket {

    private int count = 200;

    ReentrantLock lock = new ReentrantLock();

    Object mylock = new Object();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024));
        SaleTicket saleTicket = new SaleTicket();
        for (int i = 0; i < 3; i++) {
            Consumer consumer = saleTicket.new Consumer();
            threadPoolExecutor.execute(consumer);
        }
    }


    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (count > 0) {
                        count--;
                        System.out.println(Thread.currentThread().getName() + " 当前还剩余 " + count);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }


}
