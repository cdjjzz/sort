package com.sort;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DudanBeanToXMLEncoder {
	  public static void main(String[] args) {  
	        FileInputStream fos = null;  
	        BufferedInputStream bos = null;  
	        try {  
	            fos = new FileInputStream("user.xml");  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	        bos = new BufferedInputStream(fos);  
	        XMLDecoder encoder = new XMLDecoder(bos,new Object());  
	        encoder.setOwner(new Object());
	        encoder.setExceptionListener(new ExceptionListener() {
				@Override
				public void exceptionThrown(Exception e) {
					System.out.println(e.getMessage());
					
				}
			});
	        User user = (User) encoder.readObject();  
	        System.out.println(user.getAge());  
	        System.out.println(user.getId());  
	        System.out.println(user.getName());  
	        encoder.close();  
	    }  
}
