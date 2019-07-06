import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoyu
 * @date 2019-01-19
 */
public class SynchronizedExample {

    public static void synchronizedTest1(String type) {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                String s = String.format("test1 - %s: %s", type, i);
                System.out.println(s);
            }
        }
    }

    public static synchronized void synchronizedTest2(String type) {
        for (int i = 0; i < 10; i++) {
            String s = String.format("test1 - %s: %s", type, i);
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample1 = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.synchronizedTest1("type1");
        });

        executorService.execute(() -> {
            synchronizedExample2.synchronizedTest1("type2");
        });
    }

}
