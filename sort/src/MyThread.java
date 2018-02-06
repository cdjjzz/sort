public class MyThread extends Thread {
    public void run(){
    	System.out.println(this);
    	System.out.println(Thread.currentThread());
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("线程被停止了！");
                return;
            }
            System.out.println("Time: " + System.currentTimeMillis());
        }
    }
}

