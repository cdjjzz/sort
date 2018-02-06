package com.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ComplerTest {
	
	 public static void main(String[] args) {
//		System.out.println(Compiler.compileClass(Compiler.class)
//				);
		System.out.println( System.getProperty("java.vm.info"));
		Object o=Compiler.command(null);
		System.out.println(o);
		try {
		 	Process process=Runtime.getRuntime().exec("ipconfig -all");
		 	InputStream stream=process.getInputStream();
		 //	BufferedReader reader=new BufferedReader(new InputStreamReader(stream, Charset.forName("gbk")));
		 	BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
		 	while(true){
		 		String context=reader.readLine();
		 		if(context==null)
		 			break;
		 		System.out.println(context);
		 	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }

}
