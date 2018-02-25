package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;

public class Test {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Path path = Paths.get("d:/BugReport.txt");
		
		BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
		System.out.println("����ʱ�� : " + new Date(basicFileAttributes.creationTime().toMillis()));
		
		DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
		dosFileAttributeView.setHidden(true);
		
		// д��һ������
		UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		userDefinedFileAttributeView.write("��Ȩ��", Charset.defaultCharset().encode("������"));
		
		List<String> attrNames = userDefinedFileAttributeView.list(); // ������������
		for (String name: attrNames) {
			ByteBuffer bb = ByteBuffer.allocate(userDefinedFileAttributeView.size(name)); // ׼��һ����ڴ���ȡ
			userDefinedFileAttributeView.read(name, bb);
			bb.flip();
			String value = Charset.defaultCharset().decode(bb).toString();
			System.out.println(name + " : " + value);
		}
	}
}