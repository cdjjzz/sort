package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.EnumSet;
import java.util.Iterator;

public class Snippet {
	
	public static void main1(String[] args) {
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
	    final Path filePath = Paths.get("test");    //要将接收的文件写到当前目录的test文件中
	    FileChannel fileChannel = (FileChannel.open(filePath, 
	            EnumSet.of(StandardOpenOption.CREATE_NEW, 
	                    StandardOpenOption.WRITE, 
	                    StandardOpenOption.TRUNCATE_EXISTING))); 
	
	    //先获取文件大小，这里我约定前8个字节表示文件大小
	    ByteBuffer buf = ByteBuffer.allocate(8);
	    socketChannel.read(buf);
	    buf.flip();  
	    long fileSize = buf.getLong();
	    System.out.println("fileSize :"  + fileSize);
	
	    //接收文件内容pat
	    fileChannel.transferFrom(socketChannel, 0, fileSize);
	    fileChannel.close();
	}
	public static void main(String[] args) throws IOException {
		final Path path=Paths.get("d:/BugReport.txt");
		System.out.println(Files.getAttribute(path, "size"));  
		System.out.println( Files.readAttributes(path, "*"));
		FileSystem fileSystem=path.getFileSystem();
		Iterable<FileStore> fileStores=FileSystems.getDefault().getFileStores();
		Iterator<FileStore> iterator=fileStores.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().supportsFileAttributeView(BasicFileAttributeView.class));
		}
	}
	
	
}

