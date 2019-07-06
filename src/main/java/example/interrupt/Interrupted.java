package example.interrupt;

/**
 * @author zhaoyu
 * @date 2019-03-09
 */
public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "SlleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleep thread interrupt" + sleepThread.isInterrupted());
        System.out.println("busy thread interrupt" + busyThread.isInterrupted());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                    System.out.println("sleep runner ....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("busy runner ,,,,,");
            }
        }
    }
}
