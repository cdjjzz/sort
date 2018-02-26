package nio;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


/**
 * ��FileToFileTest.java��ʵ��������TODO ��ʵ������
 * 
 * @author wangding_91@163.com 2016��2��5�� ����12:05:58
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
        System.out.println("��ʱ=" + ((System.currentTimeMillis() - start)) + "ms");
    }

   // @Test
    public void transfer2() {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String username = System.getProperty("user.name");
        FileToFile f = new FileToFile();
        //E:\\���������ѯ\\policy_biz_id_2015.dat
        File source = new File("d:/CentOS-7-x86_64-DVD-1611.iso");
        String targetFileName = "d:/1212.txt";
        File target = new File(targetFileName);
        Long start = System.currentTimeMillis();
        f.transfer2(source, target);
        System.out.println("��ʱ=" + ((System.currentTimeMillis() - start)) + "ms");
    }
}