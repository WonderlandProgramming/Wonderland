package dev.wonderworld.test.event;

import dev.wonderworld.event.Event;
import dev.wonderworld.event.EventDispatcher;

public class TestMain {

	public static void main(String[] args) {
		//Event e = new TestEvent();
		Event e2 = new TestEvent2();

		TestMain tm = new TestMain();
		
		tm.onEvent(e2);
	}

	public void onEvent(Event event){
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch("TEST", (Event e) -> (onDebugEvent(e)));
		dispatcher.dispatch("TEST2", (Event e) -> (onDebugEvent(e)));
	}
	
	public boolean onDebugEvent(Event e){
		System.out.println(e.getClass());
		return true;
	}
}
