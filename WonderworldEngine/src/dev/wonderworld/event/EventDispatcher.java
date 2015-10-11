/**
 * 
 */
package dev.wonderworld.event;

/**
 * A internal Class that calls. 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @version 0.1
 */
class EventDispatcher {
	private Event event;

	/**
	 * Sets the Event for the Dispatcher
	 * @param event is the Event that is compared.
	 */
	public EventDispatcher(Event event) {
		this.event = event;
	}

	/**
	 * This calls the event and cares of Callbacks and checks if {@code type = event.type}
	 * @param type the they of the event checked
	 * @param handler the handler that is used
	 */
	public void dispatch(int type, EventHandler handler) {
		if (event.handled)
			return;

		if (event.getType() == type)
			event.handled = handler.onEvent(event);
	}
}
