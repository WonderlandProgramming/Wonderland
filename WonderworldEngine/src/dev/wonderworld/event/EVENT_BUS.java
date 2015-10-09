package dev.wonderworld.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EVENT_BUS {

	private static HashMap<Integer, List<EventHandler>> eventList;

	public static void registerEventHandler(int eventID, EventHandler handler) {
		if (eventList == null)
			eventList = new HashMap<Integer, List<EventHandler>>();

		if (eventList.containsKey(eventID)) {
			eventList.get(eventID).add(handler);
		} else {
			List<EventHandler> batch = new ArrayList<EventHandler>();
			batch.add(handler);
			eventList.put(eventID, batch);
		}
	}

	public static void callEvent(Event e) {
		if (eventList == null)
			return;
		if(!eventList.containsKey(e.getType())) return;
		
		EventDispatcher dispatcher = new EventDispatcher(e);
		
		for (EventHandler handler : eventList.get(e.getType())) {
			dispatcher.dispatch(e.getType(), handler);
		}

	}
}
