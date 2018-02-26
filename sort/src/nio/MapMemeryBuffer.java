package nio;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
  
public class MapMemeryBuffer {  
  
    public static void main(String[] args) throws Exception {  
        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);  
        byte[] bbb = new byte[14 * 1024 * 1024];  
        FileInputStream fis = new FileInputStream("d://BugReport.txt");  
        FileOutputStream fos = new FileOutputStream("d://outFile.txt");  
        FileChannel fc = fis.getChannel();  
        long timeStar = System.currentTimeMillis();// 得到当前的时间  
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.PRIVATE, 0, fc.size());  
        fc.read(mbb.duplicate());// 1 读取  
        System.out.println(fc.size()/1024);  
        long timeEnd = System.currentTimeMillis();// 得到当前的时间  
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");  
        timeStar = System.currentTimeMillis();  
        mbb.flip();  
        fos.write(mbb.array());//2.写入  
        timeEnd = System.currentTimeMillis();  
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");  
        fos.flush();  
        fc.close();  
        fis.close();  
    }  
  
}  
