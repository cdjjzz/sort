package net.jcip.examples;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThisEscape {
    public ThisEscape(EventSource source) {
    	//实例发布EventListener 
        source.registerListener(new EventListener() {
            public void onEvent(Event e) {
            	//自己被发布出去  this.doSomething(e);
                doSomething(e);
            }
        });
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
    public static void main(String[] args) {
		
	}
}

