package nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author pet-lsf
 *
 *、创建ServerSocketChannel实例，并绑定指定端口；
2、创建Selector实例；
3、将serverSocketChannel注册到selector，并指定事件OP_ACCEPT，最底层的socket通过channel和selector建立关联；
4、如果没有准备好的socket，select方法会被阻塞一段时间并返回0；
5、如果底层有socket已经准备好，selector的select方法会返回socket的个数，而且selectedKeys方法会返回socket对应的事件（connect、accept、read or write）；
6、根据事件类型，进行不同的处理逻辑；

在步骤3中，selector只注册了serverSocketChannel的OP_ACCEPT事件
1、如果有客户端A连接服务，执行select方法时，可以通过serverSocketChannel获取客户端A的socketChannel，并在selector上注册socketChannel的OP_READ事件。
2、如果客户端A发送数据，会触发read事件，这样下次轮询调用select方法时，就能通过socketChannel读取数据，同时在selector上注册该socketChannel的OP_WRITE事件，实现服务器往客户端写数据。

 */
public class SocketServer {
	static ExecutorService executorService=Executors.newSingleThreadExecutor();
	private final static int buffersize=1024;
	/*发送数据缓冲区*/  
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);  
	
	public static void main(String[] args) throws Exception {
		//服务器创建选择器
		Selector selector=Selector.open();
		ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(9901));
		serverSocketChannel.register(selector, 
				SelectionKey.OP_ACCEPT);
		while(true){
			int n = selector.select();//阻塞
		    if (n == 0) continue;
			//得到通道中的key
		   Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();
		   while(iterator.hasNext()){
			  SelectionKey key=iterator.next();
			  if(key.isAcceptable()){
				  handlerAccept(key);
			  }
			  if(key.isReadable()){
				  handleRead(key);
			  }
			  if(key.isWritable()){
				  handleWrite(key);
			  }
			  //处理完key，从注册中移除
			  iterator.remove();
		   }
		}
		
	}
	
	private static void handlerAccept(SelectionKey key) throws IOException{
		SocketChannel clientchannel = ((ServerSocketChannel)key.channel()).accept();  
        clientchannel.configureBlocking(false);  
        clientchannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(buffersize));  
	}
	 private static void handleRead(SelectionKey key)throws IOException{  
	        SocketChannel clientchannel = (SocketChannel)key.channel();  
	        //得到并清空缓冲区  
	        ByteBuffer buffer  =ByteBuffer.allocate(1024);
	        //读取信息获得读取的字节数  
	        int bytesRead = clientchannel.read(buffer);  
	          
	        if(bytesRead == -1){  
	            //没有读取到内容  
	            clientchannel.close();  
	        }else{  
	            //将缓冲区准备为数据传出状态  
	            buffer.flip();  
	            String receivedString =new  String(buffer.array(),0,bytesRead,"utf-8");  
	            //打印  
	            System.out.println("客户端"+clientchannel.socket().getRemoteSocketAddress()+":"+receivedString);  
	            //为下一次读取或写入做准备  
	            key.interestOps(SelectionKey.OP_WRITE);  
	        }  
	 }  
	 private static void handleWrite(SelectionKey key)throws IOException{  
		 SocketChannel clientchannel = (SocketChannel)key.channel(); 
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
                         clientchannel.write(sBuffer);  
                     }  
                     catch (IOException e) {  
                         e.printStackTrace();  
                         break;  
                     }  
                 }  
             
				}
			});
		 key.interestOps(SelectionKey.OP_READ); 
	 }  

}
