package com.sort;

public class ComplerTest {
	
	 public static void main(String[] args) {
		System.out.println(Compiler.compileClass(Compiler.class)
				);
		System.out.println( System.getProperty("java.vm.info"));
		Object o=Compiler.command("javac");
	 }

}
