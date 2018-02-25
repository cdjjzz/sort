package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Snippet {
	
	public static void main(String[] args) {
	    if(args.length != 1){
	        System.out.println("Usage : java -jar ServerTcpListener.jar port");
	        return;
	    }
	
	    try {
	        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
	        serverSocketChannel.socket().bind(new InetSocketAddress(Integer.parseInt(args[0])));
	
	        Thread th = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    serverSocketChannel.configureBlocking(false);
	
	                    while(true){
	                        SocketChannel socketChannel =
	                                serverSocketChannel.accept();
	                        if(socketChannel != null){
	                           receiveFile(socketChannel);
	                        }
	                    }
	
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	
	        });
	        th.run();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void receiveFile(SocketChannel socketChannel) throws IOException {
	    final Path filePath = Paths.get("test");    //Ҫ�����յ��ļ�д����ǰĿ¼��test�ļ���
	
	    FileChannel fileChannel = (FileChannel.open(filePath, 
	            EnumSet.of(StandardOpenOption.CREATE_NEW, 
	                    StandardOpenOption.WRITE, 
	                    StandardOpenOption.TRUNCATE_EXISTING))); 
	
	    //�Ȼ�ȡ�ļ���С��������Լ��ǰ8���ֽڱ�ʾ�ļ���С
	    ByteBuffer buf = ByteBuffer.allocate(8);
	    socketChannel.read(buf);
	    buf.flip();  
	    long fileSize = buf.getLong();
	    System.out.println("fileSize :"  + fileSize);
	
	    //�����ļ�����
	    fileChannel.transferFrom(socketChannel, 0, fileSize);
	    fileChannel.close();
	}
	
	
}
