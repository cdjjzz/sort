package net.jcip.examples;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SingleThreadWebServer
 * <p/>
 * Sequential web server
 *
 * @author Brian Goetz and Tim Peierls
 */

public class SingleThreadWebServer {
	private static AtomicInteger atomicInteger=new AtomicInteger(0);
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(true){
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) throws IOException {
    	BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(connection.getOutputStream());
        Writer writer=new OutputStreamWriter(bufferedOutputStream);
        writer.write("ÎÒÊÇÂÞÊ¢·á:"+atomicInteger.getAndSet(10));
        writer.close();
        // request-handling logic here
    }
}
