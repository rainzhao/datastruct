package example.notify;

import java.util.concurrent.Semaphore;

/**
 * @author zhaoyu
 * @date 2019-02-20
 */
public class SemaphoreTest {

    private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        new Thread(semaphoreTest.new Sem()).start();


        new Thread(() -> {
            semaphore.release();
        }).start();
    }

    class Sem implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("execute ....");
            } catch (InterruptedException e) {
                System.out.println("error....");
                e.printStackTrace();
            } finally {
                System.out.println("release....");
                semaphore.release();
            }
        }
    }

}
