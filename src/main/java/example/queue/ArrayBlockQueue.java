package example.queue;

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
     * @return
     */
    public synchronized int size() {
        return count;
    }


    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }



}
