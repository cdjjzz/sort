package net.jcip.examples;

import java.util.*;
import java.util.concurrent.*;

/**
 * BoundedHashSet
 * <p/>
 * Using Semaphore to bound a collection
 *
 * ����  countdownLanch   
 *
 * @author Brian Goetz and Tim Peierls
 */
public class BoundedHashSet <T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();//��ȡ����
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }
    public static void main(String[] args) throws InterruptedException{
    	BoundedHashSet<String> boundedHashSet=new BoundedHashSet<String>(3);
    	for (int i = 0; i < 10; i++) {
			boundedHashSet.add("��ʢ��"+i);
			boundedHashSet.remove("��ʢ��"+i);
		}
	}

	@Override
	public String toString() {
		return "BoundedHashSet [set=" + set + ", sem=" + sem + "]";
	}
    
}
