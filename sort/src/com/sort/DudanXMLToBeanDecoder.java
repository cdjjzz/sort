package com.sort;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DudanXMLToBeanDecoder {
	
	public static void main(String[] args) {  
        FileOutputStream fos = null;  
        BufferedOutputStream bos = null;  
        try {  
            fos = new FileOutputStream("user.xml");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
        bos = new BufferedOutputStream(fos);  
        XMLEncoder encoder = new XMLEncoder(bos);  
        User user = new User();  
        user.setAge(10);  
        user.setBirth(new Date());  
        user.setId("0x4535435");  
        user.setName("¶Åµ¤");  
        user.setPassword("111111");  
        user.setScore(98);  
        List list = new ArrayList();  
        Role role = null;  
        for (int i = 0; i < 5; i++) {  
            role = new Role();  
            role.setId(i);  
            role.setRoleName("½ÇÉ«" + i);  
            role.setRoleCode(String.valueOf(10 + i));  
            list.add(role);  
        }  
        user.setList(list);  
        encoder.writeObject(user);  
        encoder.close();  
    }  
}
