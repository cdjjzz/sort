package net.jcip.examples;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPerTaskWebServer
 * <p/>
 * Web server that starts a new thread for each request
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThreadPerTaskWebServer {
	private static AtomicInteger atomicInteger=new AtomicInteger(0);
	
	private  static ExecutorService executor=Executors.newFixedThreadPool(5);
	
	private  static Executor executor2=Executors.newFixedThreadPool(10);
	
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    try {
						handleRequest(connection);
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            };
            //一个请求启用一个线程
         //  new Thread(task).start();
            
            //使用线程池 复用线程
           // executor.execute(task);
            executor.submit(task);
        }
    }

    private static void handleRequest(Socket connection) throws IOException {
        // request-handling logic here
    	System.out.println(connection.getLocalPort());
    	System.out.println(connection.getSoLinger());
    	BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(connection.getOutputStream());
        Writer writer=new OutputStreamWriter(bufferedOutputStream);
        writer.write(Thread.currentThread().getName()+"我是罗盛丰:"+atomicInteger.getAndIncrement());
        writer.close();
    }
}
