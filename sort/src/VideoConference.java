import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable{
    private final CountDownLatch controller;
    
    public VideoConference(int number) {
        controller=new CountDownLatch(number);
    }
    public void arrive(String name){
        System.out.printf("%s has arrived.\n",name);

        controller.countDown();//调用countDown()方法，使内部计数器减1
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
    }
    
    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
        try {

            controller.await();//等待，直到CoutDownLatch计数器为0

            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}