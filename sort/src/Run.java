public class Run {
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        System.out.println(Thread.currentThread());
        Thread.sleep(2000);
    }
}