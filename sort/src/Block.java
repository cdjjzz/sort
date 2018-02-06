import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Block {
	
	public static void main(String[] args) {
		new Thread(new Block2()).start();
	}

}
class Mux{
	Lock lock=new ReentrantLock();
	public Mux() {
		lock.lock();//»ñÈ¡Ëø
	}
	public void f(){
		try {
			lock.lock();
			System.out.println("lock  aq f()");
		} catch (Exception e) {
			System.out.println("InterruptedException f()");
		}
	}
}
class Block2 implements Runnable{
	Mux mux=new Mux();
	@Override
	public void run() {
		Thread.currentThread().interrupt();
		mux.f();
	}
}
