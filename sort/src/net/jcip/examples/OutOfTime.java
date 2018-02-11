package net.jcip.examples;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * OutOfTime
 * <p/>
 * Class illustrating confusing Timer behavior
 *
 * @author Brian Goetz and Tim Peierls
 */

public class OutOfTime {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
        //MINUTES.sleep(5);
        System.out.println("1");
    }

    static class ThrowTask extends TimerTask {
        public void run() {
        	System.out.println("//////");
           // throw new RuntimeException();
        }
    }
}
