/**
 * 
 */
package dev.wonderworld.event;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @vesion 0.1
 */
public class EventDispatcher {
	private Event event;

	public EventDispatcher(Event event) {
		this.event = event;
	}

	public void dispatch(String type, EventHandler handler) {
		if (event.handled)
			return;

		if (event.getType() == type)
			event.handled = handler.onEvent(event);
	}
}
