package nio;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


/**
 * 类FileToFileTest.java的实现描述：TODO 类实现描述
 * 
 * @author wangding_91@163.com 2016年2月5日 下午12:05:58
 */
public class FileToFileTest {

    @Test
    public void FileToFile() {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String username = System.getProperty("user.name");
        FileToFile f = new FileToFile();
        File source = new File("d:/CentOS-7-x86_64-DVD-1611.iso");
        String targetFileName = "d:/1212.txt";
        File target = new File(targetFileName);
        Long start = System.currentTimeMillis();
        f.transfer(source, target);
        System.out.println("耗时=" + ((System.currentTimeMillis() - start)) + "ms");
    }

   // @Test
    public void transfer2() {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String username = System.getProperty("user.name");
        FileToFile f = new FileToFile();
        //E:\\生产问题查询\\policy_biz_id_2015.dat
        File source = new File("d:/CentOS-7-x86_64-DVD-1611.iso");
        String targetFileName = "d:/1212.txt";
        File target = new File(targetFileName);
        Long start = System.currentTimeMillis();
        f.transfer2(source, target);
        System.out.println("耗时=" + ((System.currentTimeMillis() - start)) + "ms");
    }
}