package net.jcip.examples;

import java.util.Arrays;

/**
 * UnsafeStates
 * <p/>
 * Allowing internal mutable state to escape
 *
 * @author Brian Goetz and Tim Peierls
 */
class UnsafeStates {
    private String[] states = new String[]{
        "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }
    public static void main(String[] args) {
    	//发布溢出，导致任何调用者都可以修改数组
		UnsafeStates unsafeStates=new UnsafeStates();
		   String [] s=unsafeStates.getStates();
		   System.out.println(unsafeStates);
		   s[0]="罗盛丰";
		   System.out.println(unsafeStates);
	}
	@Override
	public String toString() {
		return "UnsafeStates [states=" + Arrays.toString(states) + "]";
	}
    
}
