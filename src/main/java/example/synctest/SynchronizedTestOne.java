package example.synctest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoyu
 * @date 2019-08-21
 */
public class SynchronizedTestOne {


    @Test
    public void test() throws Exception{
        SynchronizedTestOne example1 = new SynchronizedTestOne();
        SynchronizedTestOne example2 = new SynchronizedTestOne();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 场景一：作用于同一个对象，顺序执行
        /*executorService.execute(() -> {
            example1.print("thread a");
        });

        executorService.execute(() -> {
            example1.print("thread b");
        });*/

        // 场景二：不同的对象，交替执行，互不影响
        executorService.execute(() -> {
            example1.print("thread a");
        });

        executorService.execute(() -> {
            example2.print("thread b");
        });

        Thread.sleep(5000);
    }

    /**
     * sychronized 作用于代码块
     *
     * @param thread
     */
    public void print(String thread) {
        synchronized (this) {
            if (thread.equals("thread a")) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("test1: " + thread + ":----" + i);
            }
        }
    }

    /**
     * synchronized 作用于方法
     *
     * @param thread
     */
    public synchronized void print2(String thread) {
        for (int i = 0; i < 10; i++) {
            System.out.println("test2: " + thread + ":----" + i);
        }
    }


}
