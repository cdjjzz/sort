package nio.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class SocketServer {
	static ExecutorService executorService=Executors.newFixedThreadPool(5);
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=new ServerSocket(9801);
		
			while(true){
				Socket socket=serverSocket.accept();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							  while(true){
							   BufferedReader bufferedReader=new BufferedReader(
										new InputStreamReader(socket.getInputStream()));
							    //调用接口自动回复  http://www.tuling123.com 注册获取key useid
							    //http://www.tuling123.com/openapi/api
							   String sendMsg=bufferedReader.readLine();
							   System.out.println(sendMsg);
							    URL urL=new URL("http://www.tuling123.com/openapi/api");
							    HttpURLConnection connection=(HttpURLConnection)urL.openConnection();
							   
							    connection.setDoOutput(true);
							    connection.setDoInput(true);
							    connection.setRequestMethod("POST");
							    connection.setRequestProperty("Connection", "Keep-Alive");
							    //发送json数据
							    connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
							    connection.setRequestProperty("accept","application/json");
							    String jsonStr="{\"key\":\"dbc331197a0d43f1b68671240b2e5b5a\",\"info\":\""+sendMsg+"\",\"userid\":\"224310\"}";
							    OutputStream outwritestream = connection.getOutputStream();
				                outwritestream.write(jsonStr.getBytes());
				                outwritestream.flush();
				                String result=null;
				                if (connection.getResponseCode() == 200) {
				                	BufferedReader reader = new BufferedReader(
				                            new InputStreamReader(connection.getInputStream(),"utf-8"));
				                	result= reader.readLine();
				                }
				                System.out.println(result);
				                if(result!=null){
									PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
									printWriter.write(Thread.currentThread().getName()+"随机回复:"+
											new Gson().fromJson(result, Map.class).get("text")+"\r\n");
									printWriter.flush();
				                }
							 }
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				});
		}
		//serverSocket.close();
	}

}
