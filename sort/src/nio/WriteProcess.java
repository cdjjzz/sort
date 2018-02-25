package nio;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class WriteProcess {

    public static void main(String[] arg) throws Exception {
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;

        try {
            randomAccessFile = new RandomAccessFile("d:/BugReport.txt", "rw");
            channel = randomAccessFile.getChannel();
            FileLock lock = null;

            while (null == lock) {
                try {
                    lock = channel.lock();
                } catch (Exception e) {
                    System.out.println("Write Process : get lock failed");
                }
            }

            System.out.println("Write Process : get lock");

            for (int i = 0; i < 30; i++) {
                randomAccessFile.writeByte(i);
                System.out.println("Write Process : write " + i);

                Thread.sleep(1000);
            }

            lock.release();
            System.out.println("Write Process : release lock");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != randomAccessFile) {
                randomAccessFile.close();
            }
            if (null != channel) {
                channel.close();
            }
        }
    }
}