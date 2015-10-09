package dev.wonderworld.test.event;

import dev.wonderworld.event.EVENT_BUS;
import dev.wonderworld.event.Event;

public class TestMain {

	public static void main(String[] args) {
		Event e2 = new TestEvent2();

		TestMain tm = new TestMain();
		
		EVENT_BUS.registerEventHandler(0, (Event e) -> (tm.onDebugEvent(e)));
		EVENT_BUS.registerEventHandler(1, (Event e) -> (tm.onDebugEvent(e)));
		
		EVENT_BUS.callEvent(e2);
	}
	
	public boolean onDebugEvent(Event e){
		System.out.println(e.getClass());
		return true;
	}
}
