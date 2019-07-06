package example.notify;

/**
 * @author zhaoyu
 * @date 2019-03-09
 */
public class Join {
    public static void main(String[] args) {
        /*Thread currentThread = Thread.currentThread();
        Thread thread = new Thread(new Domin(Thread.currentThread()), "main ");
        thread.start();

        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread(new Domin(currentThread), String.valueOf(i));
            thread1.start();
            currentThread = thread1;
        }*/
        Thread threadMain = new Thread(new normal(), "normal");
        Thread threadB = new Thread(new B(threadMain), "B");
        Thread threadA = new Thread(new A(threadB), "A");
        threadMain.start();
        threadB.start();
        threadA.start();



    }

    static class normal implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " thread run !");
        }
    }

    static class B implements Runnable {

        private Thread thread;

        public B(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " thread run !");
        }
    }

    static class A implements Runnable {

        private Thread thread;

        public A(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " thread run ");
        }
    }

    static class Domin implements Runnable {
        private Thread thread;

        public Domin(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "latest terminated....");
        }
    }
}
