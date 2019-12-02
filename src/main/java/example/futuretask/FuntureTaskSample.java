package example.futuretask;

import java.util.concurrent.*;

/**
 * @author zhaoyu
 * @date 2019-08-09
 */
public class FuntureTaskSample {


    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        SubThread subThread = new SubThread();

        FutureTask<String> task = new FutureTask<>(subThread);

        executorService.submit(task);

        String s = task.get(10, TimeUnit.SECONDS);

        System.out.println(s);

        System.out.println("main end =====");

    }





    static class SubThread implements Callable<String> {
        @Override
        public String call() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1234";
        }
    }

}
