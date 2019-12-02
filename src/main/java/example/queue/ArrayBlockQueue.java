package example.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoyu
 * @date 2019-07-02
 */
public class ArrayBlockQueue<T> {

    private int putIndex;

    private int getIndex;

    private T[] data;

    private Object full = new Object();

    private Object empty = new Object();

    private int count;

    public ArrayBlockQueue(int size) {
        data = (T[]) new Object[size];
        putIndex = 0;
        getIndex = 0;
        count = 0;
    }

    public void put(T t) {
        synchronized (full) {
            while (data.length == count) {
                try {
                    full.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        synchronized (empty) {
            data[putIndex] = t;
            count++;
            putIndex++;
            if (putIndex == data.length) {
                putIndex = 0;
            }
            empty.notify();
        }
    }

    public T get() {
        synchronized (empty) {
            while (count == 0) {
                try {
                    empty.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }

        synchronized (full) {
            T ret = data[getIndex];
            data[getIndex] = null;
            getIndex++;
            count--;
            if (getIndex == data.length) {
                getIndex = 0;
            }
            full.notify();
            return ret;
        }
    }

    /**
     * 获取队列大小
     *
     * @return
     */
    public synchronized int size() {
        return count;
    }


    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1024));
        ArrayBlockQueue<Integer> blockingQueueDemo = new ArrayBlockQueue<>(10);
        for (int i = 0; i < 10; i++) {
            final int data = i;
            threadPoolExecutor.execute(() -> {
                blockingQueueDemo.put(data);
            });
        }


        threadPoolExecutor.execute(() -> {
            while (true) {
                Integer take = blockingQueueDemo.get();
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
