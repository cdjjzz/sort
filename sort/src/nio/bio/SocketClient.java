package nio.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("localhost", 9801);
		BufferedWriter bufferedWriter=
				new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bufferedWriter.write("ÄãºÃ£¬ÎÒÊÇÂÞÊ¢·á");
		bufferedWriter.flush();
		socket.shutdownOutput();
		
		BufferedReader bufferedReader=new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		System.out.println(bufferedReader.readLine());

		bufferedReader.close();
		socket.close();
		
	}
	

}
