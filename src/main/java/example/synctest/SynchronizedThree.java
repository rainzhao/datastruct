package example.synctest;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoyu
 * @date 2019-08-21
 */
public class SynchronizedThree {

    static Random random = new Random();



    @Test
    public void test() throws Exception {


        SynchronizedThree example1 = new SynchronizedThree();
        SynchronizedThree example2 = new SynchronizedThree();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 场景一：作用于同一个对象，顺序执行
        /*executorService.execute(() -> {
            example1.print("thread a");
        });

        executorService.execute(() -> {
            example1.print("thread b");
        });*/

        // 场景二：不同的对象
        executorService.execute(() -> {
            example1.print("thread a");
        });

        executorService.execute(() -> {
            example1.print2("thread b");
        });

        Thread.sleep(5000);
    }

    /**
     * sychronized 修饰一个类
     *
     * @param thread
     */
    public static  void print(String thread){
        synchronized (SynchronizedThree.class){
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2: " + thread + ":----" + i);
        }
    }
}
