package nio;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Set;

import javax.print.attribute.standard.Finishings;

public class TestWalk {
	
	public static void main(String[] args) throws IOException {
	 /* final Path[] path= new Path[1];  
	  Files.walkFileTree(Paths.get("c:/"),EnumSet.of(FileVisitOption.FOLLOW_LINKS),1100,new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问：" + dir + "目录");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("\t正在访问" + file + "文件");
				if (file.endsWith("Test.java")) {
					path[0]=file;
					System.out.println("******找到目标文件Test.java******");
					return FileVisitResult.TERMINATE; // 找到了就终止
				}
				return FileVisitResult.CONTINUE; // 没找到继续找
			}
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
				throws IOException {
			// TODO Auto-generated method stub
			   return FileVisitResult.CONTINUE;
			}
			
		});
	  System.out.println(path[0]+"///////////");*/
	
		
	}
}