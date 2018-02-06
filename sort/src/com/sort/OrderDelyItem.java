package com.sort;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderDelyItem implements Delayed {
	private String orderNum;
	
	private long expTime;
	
	private String user;
	
	public OrderDelyItem() {
	}
	
	public OrderDelyItem(String orderNum,long expTime,String user){
		this.orderNum=orderNum;
		this.expTime=TimeUnit.MILLISECONDS.convert(expTime, TimeUnit.DAYS)+System.currentTimeMillis();
		this.user=user;
	}

	@Override
	public int compareTo(Delayed o) {
		return Long.valueOf(this.expTime).compareTo(o.getDelay(null));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return expTime-System.currentTimeMillis();
	}
	public String getOrderNum() {
		return orderNum;
	}
	public String getUser() {
		return user;
	}

}
