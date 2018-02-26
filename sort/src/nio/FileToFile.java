package nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.WritableByteChannel;

/**
 * 类FileToFile.java的实现描述：TODO 类实现描述 <br>
 * 将一个文件中的内容写到另一个文件
 * 
 * @author wangding_91@163.com 2016年2月5日 上午11:55:26
 */
public class FileToFile {

    private static final int DEFAULT_BUFFER = 3 * 1024;

    /**
     * 利用通道copy文件
     * 
     * @param source
     * @param target
     */
    public void transfer(File source, File target) {

        FileInputStream in = null;
        FileOutputStream out = null;
        if (!source.exists() || !source.isFile()) {
            throw new IllegalArgumentException("file not exsits!");
        }

        if (target.exists()) {
            target.delete();
        }

        try {
            target.createNewFile();
            in = new FileInputStream(source);
            out = new FileOutputStream(target);
            FileChannel inChannel = in.getChannel();
            WritableByteChannel outChannel = out.getChannel();
            System.out.println(inChannel.size()-Integer.MAX_VALUE);
            MappedByteBuffer buffer=inChannel.map(MapMode.READ_ONLY, 0, Integer.MAX_VALUE);
            outChannel.write(buffer);
            long  v=inChannel.size()-Integer.MAX_VALUE;
            long  r=Integer.MAX_VALUE;
            while(v>0){
            	  inChannel.position(r);
                  outChannel.write(inChannel.map(MapMode.READ_ONLY,0,v>Integer.MAX_VALUE?Integer.MAX_VALUE:v));
                  r+=v>Integer.MAX_VALUE?Integer.MAX_VALUE:v;
                  v-=v>Integer.MAX_VALUE?Integer.MAX_VALUE:v;
            }
            inChannel.close();
            outChannel.close();
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传统的输入输出流copy文件
     * 
     * @param source
     * @param target
     */
    public void transfer2(File source, File target) {
        InputStream in = null;
        OutputStream out = null;
        if (!source.exists() || !source.isFile()) {
            throw new IllegalArgumentException("file not exsits!");
        }

        if (target.exists()) {
            target.delete();
        }

        byte[] buffer = new byte[DEFAULT_BUFFER];
        int n = 0;

        try {
            target.createNewFile();
            in = new BufferedInputStream(new FileInputStream(source));
            out = new BufferedOutputStream(new FileOutputStream(target));
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            out.flush();
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}