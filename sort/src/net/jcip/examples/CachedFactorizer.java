package net.jcip.examples;

import java.math.BigInteger;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * CachedFactorizer
 * <p/>
 * Servlet that caches its last request and result
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("20");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{
        		
        };
    }
     public static void main(String[] args) throws InterruptedException {
    	 Exchanger<String>  exchanger=new Exchanger<String>();//不要在主线程 调用exchanger.exchange("1111"); 否则一直阻塞
    	 
    	 //完成服务
    	// ExecutorCompletionService<String> completionService=new ExecutorCompletionService<String>;
    	 
    	 new Thread(new Runnable() {
 			
 			@Override
 			public void run() {
 				try {
 					exchanger.exchange("1111");
 				} catch (InterruptedException e) {
 				}
 			}
 		}).start();
    	 
    	 
    	 new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(exchanger.exchange("a"));
				} catch (InterruptedException e) {
				}
			}
		}).start();
    	 
    	 //初始化数组
    	 BigInteger[]  bigIntegers=new  BigInteger[]{new BigInteger("1"),new BigInteger("2"),new BigInteger("3")};
    	 
    	 System.out.println(bigIntegers[1].add(new BigInteger("10000")));
    	 
    	 
    	 System.out.println(bigIntegers[0]);
    	 
	}
}
