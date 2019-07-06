package example.notify;

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
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
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
                        while (count == full) {
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
