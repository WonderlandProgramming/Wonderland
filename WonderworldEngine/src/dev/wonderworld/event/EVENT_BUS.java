package dev.wonderworld.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * {@link EVENT_BUS} is the core of the Event System. It will manage all
 * {@link EventHandler} and make it possible to call them remotely.
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @version 0.1
 */
public class EVENT_BUS {

	private static HashMap<Integer, List<EventHandler>> eventList;

	/**
	 * This Method will register a {@link EventHandler} to be active when a
	 * Events gets called with {@link EVENT_BUS#callEvent(Event)}.
	 * 
	 * @param eventID
	 *            The {@link Integer} Id of the Event that the
	 *            {@link EventHandler} is waiting for
	 * @param handler
	 *            The {@link EventHandler} that is going to be called when the
	 *            event is hit.
	 */
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

	/**
	 * When you call this Method it will trigger all
	 * {@link EVENT_BUS#registerEventHandler(int, EventHandler)} the associated
	 * {@link EventHandler}.
	 * 
	 * @param e
	 *            The event that is called.
	 */
	public static void callEvent(Event e) {
		if (eventList == null)
			return;
		if (!eventList.containsKey(e.getType()))
			return;

		EventDispatcher dispatcher = new EventDispatcher(e);

		for (EventHandler handler : eventList.get(e.getType())) {
			dispatcher.dispatch(e.getType(), handler);
		}
	}
	
	/**
	 * This will reset all {@link EventHandler} and delete all registed Instances.
	 */
	public static void clearEventHandler(){
		eventList = null;
	}
}
