package com.sort;

import java.util.function.Function;

public class Lam {

		public static void main(String[] args) {
			    String hello = "hello lambda ";
			    Function<String, Void> func = (name) -> {
			        System.out.println(hello + name);
			        return null;
			    };
			    func.apply("haogrgr");
		}
}
