package example.notify;

/**
 * @author zhaoyu
 * @date 2019/11/27
 * @description: TODO
 */
public class JoinPrint {

    public static void main(String[] args) {
        JoinPrint joinPrint = new JoinPrint();

        Thread c = Thread.currentThread();

        for (int i = 0; i < 10; i++) {

        }

    }






    class A extends Thread {
        private Thread thread;

        public A(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class B extends Thread {
        private Thread thread;

        public B(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class C extends Thread {
        private Thread thread;

        public C(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
