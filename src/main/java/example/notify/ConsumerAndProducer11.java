package example.notify;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019/11/26
 * @description: TODO
 */
public class ConsumerAndProducer11 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition isFull = lock.newCondition();
    private Condition isEmpty = lock.newCondition();
    private int count = 0;

    public static void main(String[] args) {

        ConsumerAndProducer11 test1 = new ConsumerAndProducer11();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024));
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(test1.new Producer());
            threadPoolExecutor.execute(test1.new Consumer());
        }
    }

    public class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    if (count == 10) {
                        try {
                            isFull.await();
                        } catch (InterruptedException e) {
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


    public class Consumer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    if (count == 0) {
                        try {
                            isEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    isFull.signal();
                    System.out.println("消费者消费 目前还有-- " + count);
                } finally {
                    lock.unlock();
                }
            }
        }
    }


}
