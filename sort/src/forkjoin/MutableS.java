package forkjoin;

public class MutableS {
	
	private int value;
	
	synchronized public  int getValue() {
		return value;
	}
	synchronized public   void   setValue(int value) {
		this.value = value;
	}
	

}
