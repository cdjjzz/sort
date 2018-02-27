package nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @author pet-lsf
 *
 * 一个线程管理一个Selector(管家) 通过事件分发(SelectionKey.XXXX) 
 *  管理多个通道(SocketChannel)
 *
 *
 */
public class SocketClient {
	
	
	
	static ExecutorService executorService=Executors.newSingleThreadExecutor();
	
	private static Selector selector=null;
	
	private static SocketChannel socketChannel=null;
	/*发送数据缓冲区*/  
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);  
      
    /*接受数据缓冲区*/  
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);  
	
    public SocketClient() {
    	try {
    		//打开一个通道
    	    socketChannel=SocketChannel.open();
    		socketChannel.configureBlocking(false);//设置非阻塞
    		selector=Selector.open();//
    		//打开选择器并注册通道 在通道处注册事件 
    		socketChannel.register(selector, SelectionKey.OP_CONNECT);
    		socketChannel.connect(new InetSocketAddress("localhost",9901));
    		while(true){
    			    selector.select();
    				//通过注册在通道中的选择器获取通道
    				for(SelectionKey selectionKey:selector.selectedKeys()){
    					handle(selectionKey);
    			    }
    				selector.selectedKeys().clear();
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
	}
	
	public static void main(String[] args) throws IOException {
		new SocketClient();
		
	}
	 private static void handle(SelectionKey selectionKey) throws IOException {  
	        if (selectionKey.isConnectable()) {  
	            /*  
	             * 连接建立事件，已成功连接至服务器  
	             */  
	        	socketChannel = (SocketChannel)selectionKey.channel();  
	            if (socketChannel.isConnectionPending()) {  
	            	socketChannel.finishConnect();  
	                System.out.println("connect success !");  
	                sBuffer.clear();  
	                sBuffer.put(("connected!").getBytes("utf-8"));  
	                sBuffer.flip();  
	                socketChannel.write(sBuffer);//发送信息至服务器    
	                executorService.execute(new Runnable() {
						
						@Override
						public void run() {
							  
	                        while (true) {  
	                            try {  
	                                sBuffer.clear();  
	                                Scanner cin = new Scanner(System.in);  
	                                String sendText = cin.nextLine();  
	                                /*  
	                                 * 未注册WRITE事件，因为大部分时间channel都是可以写的  
	                                 */  
	                                sBuffer.put(sendText.getBytes("utf-8"));  
	                                sBuffer.flip();  
	                                socketChannel.write(sBuffer);  
	                            }  
	                            catch (IOException e) {  
	                                e.printStackTrace();  
	                                break;  
	                            }  
	                        }  
	                    
						}
					});
	            }  
	            //注册读事件    
	            socketChannel.register(selector, SelectionKey.OP_READ);  
	        }  
	        else if (selectionKey.isReadable()) {  
	            /*  
	             * 读事件触发  
	             * 有从服务器端发送过来的信息，读取输出到屏幕上后，继续注册读事件  
	             * 监听服务器端发送信息  
	             */  
	        	socketChannel = (SocketChannel)selectionKey.channel();  
	            rBuffer.clear();  
	            int count = socketChannel.read(rBuffer);  
	            if (count > 0) {  
	                 String  receiveText = new String(rBuffer.array(),0,count,"utf-8");  
	                System.out.println("服务器返回消息:"+receiveText);  
	                socketChannel = (SocketChannel)selectionKey.channel();  
	                socketChannel.register(selector, SelectionKey.OP_WRITE);  
	            }  
	        }  
	    }
}
