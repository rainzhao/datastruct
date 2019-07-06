package example.interrupt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @author zhaoyu
 * @date 2019-07-01
 */
public class InterruptCommunicateExp {

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                LOGGER.info(Thread.currentThread().getName() + " running ...");
            }

            LOGGER.info(Thread.currentThread().getName() + "stop...");
            countDownLatch.countDown();
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

        countDownLatch.await();


    }


}
