package example.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhaoyu
 * @date 2019-01-19
 */

public class CountDownLatchExp {

    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 50;

    public static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        executorService.shutdown();
        System.out.println("count : " + count);

    }

    private static void add() {
        count++;
    }
}
