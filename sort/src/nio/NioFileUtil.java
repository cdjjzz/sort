package nio;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * ʹ����NIOʵ���ļ��еĸ���/�ƶ�,ɾ��<br>
 * ��Ҫjava 1.7���ϰ汾֧��
 * @author guyadong
 *
 */
public class NioFileUtil {
    /**
     * �����ļ���
     * @param source
     * @param target
     * @param options
     * @throws IOException
     * @see {@link #operateDir(boolean, Path, Path, CopyOption...)}
     */
    public static void copyDir(Path source, Path target, CopyOption... options) throws IOException{
        operateDir(false, source, target, options);
    }
    /**
     * �ƶ��ļ���
     * @param source
     * @param target
     * @param options
     * @throws IOException
     * @see {@link #operateDir(boolean, Path, Path, CopyOption...)}
     */
    public static void moveDir(Path source, Path target, CopyOption... options) throws IOException{
        operateDir(true, source, target, options);
    }
    /**
     * ����/�ƶ��ļ���
     * @param move ������ǣ�Ϊtrueʱ�ƶ��ļ���,����Ϊ����
     * @param source Ҫ����/�ƶ���Դ�ļ���
     * @param target Դ�ļ���Ҫ����/�ƶ�����Ŀ���ļ���
     * @param options �ļ�����ѡ�� 
     * @throws IOException
     * @see Files#move(Path, Path, CopyOption...)
     * @see Files#copy(Path, Path, CopyOption...)
     * @see Files#walkFileTree(Path, java.nio.file.FileVisitor)
     */
    public static void operateDir(boolean move,Path source, Path target, CopyOption... options) throws IOException{
        if(null==source||!Files.isDirectory(source))
            throw new IllegalArgumentException("source must be directory");
        Path dest = target.resolve(source.getFileName());
        // �����ͬ�򷵻�
        if(Files.exists(dest)&&Files.isSameFile(source, dest))return;
        // Ŀ���ļ��в�����Դ�ļ��е����ļ���
        // isSub����ʵ�ֲμ���һƪ���� http://blog.csdn.net/10km/article/details/54425614
        if(isSub(source,dest))
            throw new IllegalArgumentException("dest must not  be sub directory of source");
        boolean clear=true;     
        for(CopyOption option:options)
            if(StandardCopyOption.REPLACE_EXISTING==option){
                clear=false;
                break;
            }
        // ���ָ����REPLACE_EXISTINGѡ�������Ŀ���ļ���
        if(clear)
            deleteIfExists(dest);
        Files.walkFileTree(source, 
                new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        // ��Ŀ���ļ����д���dir��Ӧ�����ļ���
                        Path subDir = 0==dir.compareTo(source)?dest:dest.resolve(dir.subpath(source.getNameCount(), dir.getNameCount()));
                        Files.createDirectories(subDir);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if(move)
                            Files.move(file, dest.resolve(file.subpath(source.getNameCount(), file.getNameCount())),options);
                        else
                            Files.copy(file, dest.resolve(file.subpath(source.getNameCount(), file.getNameCount())),options);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        // �ƶ�����ʱɾ��Դ�ļ���
                        if(move)
                            Files.delete(dir);
                        return super.postVisitDirectory(dir, exc);
                    }
                });
    }

    /**
     * ǿ��ɾ���ļ�/�ļ���(����Ϊ�յ��ļ���)<br>
     * @param dir
     * @throws IOException
     * @see Files#deleteIfExists(Path)
     * @see Files#walkFileTree(Path, java.nio.file.FileVisitor)
     */
    public static void deleteIfExists(Path dir) throws IOException {
        try {
            Files.deleteIfExists(dir);
        } catch (DirectoryNotEmptyException e) {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return super.postVisitDirectory(dir, exc);
                }
            });
        }
    }
    /**
     * �ж�sub�Ƿ���parent��Ȼ�����֮��<br>
     * parent������ڣ��ұ�����directory,�����׳�{@link IllegalArgumentException}
     * @param parent 
     * @param sub
     * @return
     * @throws IOException 
     */
    public static boolean sameOrSub(Path parent,Path sub) throws IOException{
        if(null==parent)
            throw new NullPointerException("parent is null");
        if(!Files.exists(parent)||!Files.isDirectory(parent))
            throw new IllegalArgumentException(String.format("the parent not exist or not directory %s",parent));
        while(null!=sub) {
            if(Files.exists(sub)&&Files.isSameFile(parent, sub))
                return true;
            sub=sub.getParent();
        }
        return false;   
    }
    /**
     * �ж�sub�Ƿ���parent֮�µ��ļ������ļ���<br>
     * parent������ڣ��ұ�����directory,�����׳�{@link IllegalArgumentException}
     * @param parent
     * @param sub
     * @return
     * @throws IOException
     * @see {@link #sameOrSub(Path, Path)}
     */
    public static boolean isSub(Path parent,Path sub) throws IOException{
        return (null==sub)?false:sameOrSub(parent,sub.getParent());
    }

}