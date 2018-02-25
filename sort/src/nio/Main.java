package nio;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Main
{

    public static void main(String[] args) throws IOException
    {
       /* ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        channelCopy1(source, dest);
        // channelCopy2 (source, dest);
        source.close();
        dest.close();
*/
    	FileOutputStream out = new FileOutputStream(FileDescriptor.out);
    	  out.write('A');
    	  out.close();
    }

    private static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest)
        throws IOException
    {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1)
        {
            // �л�Ϊ��״̬
            buffer.flip();
            // ���ܱ�֤ȫ��д��
            dest.write(buffer);
            // �ͷ��Ѷ����ݵĿռ䣬�ȴ�����д��
            buffer.compact();
        }
        // �˳�ѭ����ʱ�����ڵ��õ���compact�������������п��ܻ�������
        // ��Ҫ��һ����ȡ
        buffer.flip();
        while (buffer.hasRemaining())
        {
            dest.write(buffer);
        }
    }

    private static void channelCopy2(ReadableByteChannel src, WritableByteChannel dest)
        throws IOException
    {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1)
        {
            // �л�Ϊ��״̬
            buffer.flip();
            // ��֤������������ȫ��д��
            while (buffer.hasRemaining())
            {
                dest.write(buffer);
            }
            // ���������
            buffer.clear();
        }
        // �˳�ѭ����ʱ�����ڵ��õ���clear���������������Ѿ�û�����ݣ�����Ҫ��һ������
    }

}