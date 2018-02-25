package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;

public class FileChannle {
	public static void main(String[] args) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("D:\\CentOS-7-x86_64-DVD-1611.iso", "rw");
		FileChannel channel=aFile.getChannel();
//		MappedByteBuffer mode=channel.map(MapMode.READ_ONLY, 0, Integer.MAX_VALUE);
//		System.out.println(mode.isDirect());
//		System.out.println(mode.getInt());
//		for (int i = 0; i < mode.capacity(); i++) {
//			System.out.println(mode.get(i));
//		}
//		mode.clear();
		Thread.sleep(100000);
	}

}
