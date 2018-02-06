package com.sort;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements  Serializable {
	 private static final long serialVersionUID = 5076428480402933567L;  
	    private String id;  
	    private String name;  
	    private String password;  
	    private int age;  
	    private Integer score;  
	    private Date birth;  
	    private List list;  
	  
	    public List getList() {  
	        return list;  
	    }  
	  
	    public void setList(List list) {  
	        this.list = list;  
	    }  
	  
	    public int getAge() {  
	        return age;  
	    }  
	  
	    public void setAge(int age) {  
	        this.age = age;  
	    }  
	  
	    public Date getBirth() {  
	        return birth;  
	    }  
	  
	    public void setBirth(Date birth) {  
	        this.birth = birth;  
	    }  
	  
	    public String getId() {  
	        return id;  
	    }  
	  
	    public void setId(String id) {  
	        this.id = id;  
	    }  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	  
	    public String getPassword() {  
	        return password;  
	    }  
	  
	    public void setPassword(String password) {  
	        this.password = password;  
	    }  
	  
	    public Integer getScore() {  
	        return score;  
	    }  
	  
	    public void setScore(Integer score) {  
	        this.score = score;  
	    }  
}
