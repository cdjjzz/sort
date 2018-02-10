package net.jcip.examples;

import net.jcip.annotations.*;

/**
 * MutableInteger
 * <p/>
 * Non-thread-safe mutable integer holder
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class MutableInteger {
	@GuardedBy("this")   private int value;

    public int get() {
    	synchronized (this) {
    		return value;
		}
    }

    public void set(int value) {
    	synchronized (this) {
    		this.value = value;
		}
    }
}








