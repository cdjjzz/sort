package nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestPath {
	public static void main(String[] args) {  
	    try {  
	    	Files.list(Paths.get(".")).forEach(System.out::println);
	    	File f=new File("D:/home/sample/21.txt");
	    	f.mkdirs();
	        Path directoryPath = Paths.get("D:/home/sample/1.txt");  
	        Files.createDirectories(directoryPath);
	        System.out.println("Directory created successfully!");  
	  
	        Path filePath = Paths.get("D:/home/sample/test.txt");  
	        Files.newInputStream(filePath, StandardOpenOption.WRITE);
	        Files.createFile(filePath);  
	        System.out.println("File created successfully!");  
	  
	        Path directoriesPath = Paths.get("D:/home/sample/subtest/subsubtest");  
	        System.out.println("Sub-directory created successfully!");  
	        Files.createDirectories(directoriesPath);  
	  
	    } catch (FileAlreadyExistsException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}  
}
