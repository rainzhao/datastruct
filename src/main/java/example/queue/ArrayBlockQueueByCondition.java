package example.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019-07-02
 */
public class ArrayBlockQueueByCondition<T> {

    private int count;

    private int takeIndex;

    /**
     * 插入下一个元素的索引
     */
    private int putIndex;

    private T[] data;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public ArrayBlockQueueByCondition(int size) {
        data = (T[]) new Object[size];
        count = 0;
        takeIndex = 0;
        putIndex = 0;
    }

    /**
     * 如果可以在不超过队列容量的情况下立即插入指定元素，则在该队列的尾部插入该元素；
     * 如果成功，则返回true；如果该队列已满，则返回false。
     * @param t
     * @return
     */
    public boolean offer(T t) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count == data.length) {
                return false;
            } else {
                // 队列未满则插入
                enqueue(t);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    public T poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    public void put(T e) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        // 获取锁，若当前线程是中断状态，则抛出InterruptedException异常
        lock.lockInterruptibly();
        try {
            while (count == data.length) {
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(T e) {
        final Object[] items = this.data;
        items[putIndex] = e;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
        count ++;
        notEmpty.signal();
    }


    public T take() throws InterruptedException {
        // 获取队列的锁
        final ReentrantLock lock = this.lock;
        // 获取锁，若当前线程是中断状态，则抛出InterruptedException异常
        lock.lockInterruptibly();

        try {
            while (count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    private T dequeue() {
        T ret = data[takeIndex];
        data[takeIndex] = null;
        if (++takeIndex == data.length) {
            takeIndex = 0;
        }
        --count;
        notFull.signal();
        return ret;
    }

    public static void main(String[] args) throws Exception{
//        int size = 0;
//
//        int[] buckets = new int[2];
//        buckets[size++] = 10;
//
//        System.out.println(buckets);


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024));
        ArrayBlockQueueByCondition<Integer> blockingQueueDemo = new ArrayBlockQueueByCondition<>(10);
        for (int i = 0; i < 10; i++) {
            final int data = i;
            threadPoolExecutor.execute(() -> {
                try {
                    blockingQueueDemo.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                Integer take = null;
                try {
                    take = blockingQueueDemo.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("take data is : " + take);
            });
        }


    }



}
