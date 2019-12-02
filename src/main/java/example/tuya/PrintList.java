package example.tuya;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyu
 * @date 2019/11/27
 * @description: TODO
 */
public class PrintList {

    // 100个数放在list ，10个线程怎么有序的打印出来
    ReentrantLock lock = new ReentrantLock(true);
    List<Integer> list = new ArrayList<>(100);
    private int count = 0;
    public PrintList() {


        for (int i = 0; i < 100; i++) {
            try {
                list.add(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PrintList printList = new PrintList();

        for (int i = 0; i < 10; i++) {
            MyThread myThread = printList.new MyThread();
            myThread.start();
        }

        Thread.sleep(20000);
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            while (count < 100) {
                lock.lock();
                try {
                    Integer remove = list.get(count);
                    System.out.println(" " + remove);
                    count++;
                } catch (Exception ignore) {

                } finally {
                    lock.unlock();
                }
            }


        }
    }


}
