package example.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class ProducerAndConsumerReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();
    private Condition isFull = lock.newCondition();
    private Condition isEmpty = lock.newCondition();
    private int count = 0;
    private int full = 10;

    public ProducerAndConsumerReentrantLockTest() {
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
    }

    public static void main(String[] args) {
        ProducerAndConsumerReentrantLockTest test = new ProducerAndConsumerReentrantLockTest();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();

    }


    class Producer implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (count == full) {
                        try {
                            isFull.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    isEmpty.signal();
                } finally {
                    lock.unlock();
                }

            }

        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    while (count == 0) {
                        try {
                            isEmpty.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count --;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    isFull.signal();
                } finally {
                    lock.unlock();
                }

            }
        }
    }


}
