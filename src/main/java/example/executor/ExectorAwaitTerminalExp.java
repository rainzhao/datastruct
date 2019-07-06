package example.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @author zhaoyu
 * @date 2019-07-01
 */
public class ExectorAwaitTerminalExp {


    public static void main(String[] args) throws Exception{

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(10);

        ExecutorService executor = new ThreadPoolExecutor(5,5,1,
                TimeUnit.MILLISECONDS, queue, namedThreadFactory);

        executor.execute(() -> {
            LOGGER.info("running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread = new Thread(() -> {
            LOGGER.info("running2");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(thread);

        thread.interrupt();
        Thread.sleep(5000);
        executor.shutdown();

        while (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
            LOGGER.info("线程还在执行。。。");
        }

        LOGGER.info("main over");

    }
}
