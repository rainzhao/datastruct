import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoyu
 * @date 2019-01-17
 */
public class NormalTest {

    public Object myTest = new Object();

    @Test
    public void syncTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                doSomething();
            });
//            doSomething();
        }


    }


    public void doSomething() {
        synchronized (myTest) {
            System.out.println("do something...");
            myTest = null;
        }
    }


    @Test
    public void test() {
        boolean test1 = TestEnum.DATA.getData().equals(null);

        String str = null;
        boolean data = str.equals("data");


    }

}
