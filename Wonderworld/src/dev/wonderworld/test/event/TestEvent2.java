package dev.wonderworld.test.event;

import dev.wonderworld.event.Event;

public class TestEvent2 extends Event{
	public TestEvent2() {
		super(1);
	}

	public String getDebug() {
		return "Success event 2";
	}
}
