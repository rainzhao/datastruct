package example.count;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author zhaoyu
 * @date 2019-03-11
 */
public class CycleBarrierTest implements Runnable {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);


    private Executor executor = Executors.newFixedThreadPool(4);

    /**
     * 保存每个Sheet计算出来的结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        CycleBarrierTest cycleBarrierTest = new CycleBarrierTest();
        cycleBarrierTest.count();
    }
}
