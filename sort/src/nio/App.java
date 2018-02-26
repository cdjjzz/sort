package nio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class App {
	String generalFile = "D:/CentOS-7-x86_64-DVD-1611.iso";

	public void generalFileRead(String fileName) {
		try {
			System.out.print("readGeneralFile");
			FileInputStream in = new FileInputStream(fileName);
			readIn(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void randomFileRead(String fileName) {
		try {
			System.out.print("readRandomFile");
			RandomAccessFile file = new RandomAccessFile(fileName, "r");
			int i = file.read();
			while (i != -1) {
				i = file.read();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bufferRead(String fileName) {
		try {
			System.out.print("readBufferInput");
			InputStream in = new BufferedInputStream(new FileInputStream(
					fileName));
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			while(bf.readLine()!=null){
				bf.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readIn(InputStream in) throws IOException {
		int i = in.read();
		while (i != -1) {
			i = in.read();
		}
	}

	public void mappedByteBufferRead(String fileName) {
		try {
			System.out.println("readMappedFile");

			FileChannel channel = new  RandomAccessFile(new  File(fileName), "rw").getChannel();
			MappedByteBuffer buffer = channel.map(
					FileChannel.MapMode.READ_ONLY, 0,Integer.MAX_VALUE+1);
			while(buffer.remaining()>0){
				buffer.get();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void countTime(long start) {
		System.out.println(":" + (System.currentTimeMillis() - start) + "ms");
	}

	public static void main(String[] args) {
		App app = new App();

		long start = System.currentTimeMillis();
		 /*app.generalFileRead(app.generalFile);
		 app.countTime(start);
		
		 start = System.currentTimeMillis();
		 app.randomFileRead(app.generalFile);
		 app.countTime(start);*/
		
//		 start = System.currentTimeMillis();
//		 app.bufferRead(app.generalFile);
//		 app.countTime(start);
		
		start = System.currentTimeMillis();
		app.mappedByteBufferRead(app.generalFile);
		app.countTime(start);

	}

}
