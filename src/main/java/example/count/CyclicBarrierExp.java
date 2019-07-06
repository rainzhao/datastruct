package example.count;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @author zhaoyu
 * @date 2019-07-01
 */
public class CyclicBarrierExp {

    private static void cyclicBarrier() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new Thread(() -> {
            LOGGER.info("thread run");

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            LOGGER.info(Thread.currentThread().getName() + " do something");
        }).start();

        new Thread(() -> {
            LOGGER.info("thread run ");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            LOGGER.info(Thread.currentThread().getName() + " do something");
        }).start();

        new Thread(() -> {
            LOGGER.info("thread run");
            try {
                Thread.sleep(5000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            LOGGER.info(Thread.currentThread().getName() + " do something");
        }).start();



    }

    public static void main(String[] args) throws Exception{
        CyclicBarrierExp.cyclicBarrier();
    }
}
