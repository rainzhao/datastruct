package example.queue;

/**
 * @author zhaoyu
 * @date 2019-02-18
 */
public class LoopQueue<E> {
    private E[] data;

    private int front, tail;

    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;

    }

    public LoopQueue() {
        this(10);
    }

    private int getCapcity() {
        return data.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int getSize() {
        return size;
    }

    public void enqueue(E e) {
        if (getSize() == getCapcity()) {
            // 扩容
            resize(getCapcity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E dequeue() {
        if(isEmpty()) {
            throw new IllegalArgumentException("can not dequeue from an empty queue");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (getCapcity() / 4 == size && getCapcity() / 2 != 0) {
            resize(getCapcity() / 2);
        }
        return ret;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    private void print() {
        System.out.println(data);
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for(int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
        }


        loopQueue.dequeue();
        loopQueue.dequeue();

        loopQueue.enqueue(11);
        loopQueue.enqueue(12);

        loopQueue.print();

        loopQueue.enqueue(13);
        loopQueue.enqueue(14);

        for (int i = 0; i < 18; i++) {
            loopQueue.dequeue();
        }
    }
}
