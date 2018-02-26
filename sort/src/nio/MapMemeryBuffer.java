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
        long timeStar = System.currentTimeMillis();// �õ���ǰ��ʱ��  
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.PRIVATE, 0, fc.size());  
        fc.read(mbb.duplicate());// 1 ��ȡ  
        System.out.println(fc.size()/1024);  
        long timeEnd = System.currentTimeMillis();// �õ���ǰ��ʱ��  
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");  
        timeStar = System.currentTimeMillis();  
        mbb.flip();  
        fos.write(mbb.array());//2.д��  
        timeEnd = System.currentTimeMillis();  
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");  
        fos.flush();  
        fc.close();  
        fis.close();  
    }  
  
}  
