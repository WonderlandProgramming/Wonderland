package dev.wonderworld.test.event;

import dev.wonderworld.event.Event;

public class TestEvent extends Event{

	public TestEvent() {
		super("TEST");
	}

	public String getDebug(){
		return "Success";
	}
}
