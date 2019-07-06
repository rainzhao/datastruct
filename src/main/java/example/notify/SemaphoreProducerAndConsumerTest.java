package example.notify;

import java.util.concurrent.Semaphore;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class SemaphoreProducerAndConsumerTest {

    private Semaphore notFull = new Semaphore(2);
    private Semaphore notEmpty = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private int count = 0;

    public static void main(String[] args) {
        SemaphoreProducerAndConsumerTest test = new SemaphoreProducerAndConsumerTest();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    notFull.acquire();
                    mutex.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName() + "produce has ：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    notEmpty.release();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    notEmpty.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "consumer ：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    notFull.release();
                }

            }
        }
    }

}
