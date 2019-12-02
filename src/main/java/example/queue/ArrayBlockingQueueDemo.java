package example.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019/11/27
 * @description: TODO
 */
public class ArrayBlockingQueueDemo<T> {

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    private T[] data;

    private int putIndex;

    private int takeIndex;

    public ArrayBlockingQueueDemo(int size) {
        this.count = 0;
        this.data = (T[]) new Object[size];
        this.putIndex = 0;
        this.takeIndex = 0;
    }

    public void put(T t) {
        lock.lock();
        try {
            while (count == data.length) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data[putIndex] = t;
            putIndex++;
            if (putIndex == data.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();

        try {
            while (count == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T ret = data[takeIndex];
            data[takeIndex] = null;
            takeIndex++;
            if (takeIndex == data.length) {
                takeIndex = 0;
            }
            --count;
            notFull.signal();
            return ret;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024));
        ArrayBlockingQueueDemo<Integer> blockingQueueDemo = new ArrayBlockingQueueDemo<>(10);
        for (int i = 0; i < 10; i++) {
            final int data = i;
            threadPoolExecutor.execute(() -> {
                blockingQueueDemo.put(data);
            });
        }


        threadPoolExecutor.execute(() -> {
            while (true) {
                Integer take = blockingQueueDemo.take();
                System.out.println("take data is : " + take);
            }
        });


        for (int i = 10; i < 20; i++) {
            final int data = i;
            threadPoolExecutor.execute(() -> {
                blockingQueueDemo.put(data);
            });
        }


    }


}
