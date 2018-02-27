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
 * һ���̹߳���һ��Selector(�ܼ�) ͨ���¼��ַ�(SelectionKey.XXXX) 
 *  ������ͨ��(SocketChannel)
 *
 *
 */
public class SocketClient {
	
	
	
	static ExecutorService executorService=Executors.newSingleThreadExecutor();
	
	private static Selector selector=null;
	
	private static SocketChannel socketChannel=null;
	/*�������ݻ�����*/  
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);  
      
    /*�������ݻ�����*/  
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);  
	
    public SocketClient() {
    	try {
    		//��һ��ͨ��
    	    socketChannel=SocketChannel.open();
    		socketChannel.configureBlocking(false);//���÷�����
    		selector=Selector.open();//
    		//��ѡ������ע��ͨ�� ��ͨ����ע���¼� 
    		socketChannel.register(selector, SelectionKey.OP_CONNECT);
    		socketChannel.connect(new InetSocketAddress("localhost",9901));
    		while(true){
    			    selector.select();
    				//ͨ��ע����ͨ���е�ѡ������ȡͨ��
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
	             * ���ӽ����¼����ѳɹ�������������  
	             */  
	        	socketChannel = (SocketChannel)selectionKey.channel();  
	            if (socketChannel.isConnectionPending()) {  
	            	socketChannel.finishConnect();  
	                System.out.println("connect success !");  
	                sBuffer.clear();  
	                sBuffer.put(("connected!").getBytes("utf-8"));  
	                sBuffer.flip();  
	                socketChannel.write(sBuffer);//������Ϣ��������    
	                executorService.execute(new Runnable() {
						
						@Override
						public void run() {
							  
	                        while (true) {  
	                            try {  
	                                sBuffer.clear();  
	                                Scanner cin = new Scanner(System.in);  
	                                String sendText = cin.nextLine();  
	                                /*  
	                                 * δע��WRITE�¼�����Ϊ�󲿷�ʱ��channel���ǿ���д��  
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
	            //ע����¼�    
	            socketChannel.register(selector, SelectionKey.OP_READ);  
	        }  
	        else if (selectionKey.isReadable()) {  
	            /*  
	             * ���¼�����  
	             * �дӷ������˷��͹�������Ϣ����ȡ�������Ļ�Ϻ󣬼���ע����¼�  
	             * �����������˷�����Ϣ  
	             */  
	        	socketChannel = (SocketChannel)selectionKey.channel();  
	            rBuffer.clear();  
	            int count = socketChannel.read(rBuffer);  
	            if (count > 0) {  
	                 String  receiveText = new String(rBuffer.array(),0,count,"utf-8");  
	                System.out.println("������������Ϣ:"+receiveText);  
	                socketChannel = (SocketChannel)selectionKey.channel();  
	                socketChannel.register(selector, SelectionKey.OP_WRITE);  
	            }  
	        }  
	    }
}
