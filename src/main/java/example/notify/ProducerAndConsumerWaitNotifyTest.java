package example.notify;

import java.util.concurrent.*;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class ProducerAndConsumerWaitNotifyTest {

    private Object lock = new Object();

    private int count = 0;

    private int full = 10;

    public static void main(String[] args) {
        ProducerAndConsumerWaitNotifyTest test1 = new ProducerAndConsumerWaitNotifyTest();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024));
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(test1.new Producer());
            threadPoolExecutor.execute(test1.new Consumer());
        }
    }


    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    try {
                        if (count == 0) {
                            lock.wait();
                        }
                        count--;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    lock.notifyAll();
                }
            }
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    try {
                        if (count == full) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    lock.notifyAll();
                }
            }
        }
    }
}
