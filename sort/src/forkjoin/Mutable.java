package forkjoin;

public class Mutable {
	
	private int value;
	
	public int getValue() {
		synchronized (this) {
			return value;
		}
	}
	public void setValue(int value) {
		synchronized (this) {
			this.value = value;
		}
	}
	

}
