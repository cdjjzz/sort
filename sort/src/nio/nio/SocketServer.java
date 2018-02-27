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
 *������ServerSocketChannelʵ��������ָ���˿ڣ�
2������Selectorʵ����
3����serverSocketChannelע�ᵽselector����ָ���¼�OP_ACCEPT����ײ��socketͨ��channel��selector����������
4�����û��׼���õ�socket��select�����ᱻ����һ��ʱ�䲢����0��
5������ײ���socket�Ѿ�׼���ã�selector��select�����᷵��socket�ĸ���������selectedKeys�����᷵��socket��Ӧ���¼���connect��accept��read or write����
6�������¼����ͣ����в�ͬ�Ĵ����߼���

�ڲ���3�У�selectorֻע����serverSocketChannel��OP_ACCEPT�¼�
1������пͻ���A���ӷ���ִ��select����ʱ������ͨ��serverSocketChannel��ȡ�ͻ���A��socketChannel������selector��ע��socketChannel��OP_READ�¼���
2������ͻ���A�������ݣ��ᴥ��read�¼��������´���ѯ����select����ʱ������ͨ��socketChannel��ȡ���ݣ�ͬʱ��selector��ע���socketChannel��OP_WRITE�¼���ʵ�ַ��������ͻ���д���ݡ�

 */
public class SocketServer {
	static ExecutorService executorService=Executors.newSingleThreadExecutor();
	private final static int buffersize=1024;
	/*�������ݻ�����*/  
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);  
	
	public static void main(String[] args) throws Exception {
		//����������ѡ����
		Selector selector=Selector.open();
		ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(9901));
		serverSocketChannel.register(selector, 
				SelectionKey.OP_ACCEPT);
		while(true){
			int n = selector.select();//����
		    if (n == 0) continue;
			//�õ�ͨ���е�key
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
			  //������key����ע�����Ƴ�
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
	        //�õ�����ջ�����  
	        ByteBuffer buffer  =ByteBuffer.allocate(1024);
	        //��ȡ��Ϣ��ö�ȡ���ֽ���  
	        int bytesRead = clientchannel.read(buffer);  
	          
	        if(bytesRead == -1){  
	            //û�ж�ȡ������  
	            clientchannel.close();  
	        }else{  
	            //��������׼��Ϊ���ݴ���״̬  
	            buffer.flip();  
	            String receivedString =new  String(buffer.array(),0,bytesRead,"utf-8");  
	            //��ӡ  
	            System.out.println("�ͻ���"+clientchannel.socket().getRemoteSocketAddress()+":"+receivedString);  
	            //Ϊ��һ�ζ�ȡ��д����׼��  
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
                          * δע��WRITE�¼�����Ϊ�󲿷�ʱ��channel���ǿ���д��  
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
